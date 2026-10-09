package br.com.reservasegura.service;

import br.com.reservasegura.dto.*;
import br.com.reservasegura.entity.User;
import br.com.reservasegura.repository.UserRepository;
import br.com.reservasegura.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse registrar(RegisterRequest request) {
        if (repository.existsByEmail(request.email))
            throw new RuntimeException("Email já cadastrado");
        if (repository.existsByCpf(request.cpf))
            throw new RuntimeException("CPF já cadastrado");

        User user = new User();
        user.setNome(request.nome);
        user.setEmail(request.email);
        user.setCpf(request.cpf);
        user.setSenha(passwordEncoder.encode(request.senha));
        repository.save(user);

        String token = jwtService.gerarToken(user.getEmail());
        return new AuthResponse(token, user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = repository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.senha, user.getSenha()))
            throw new RuntimeException("Senha incorreta");

        String token = jwtService.gerarToken(user.getEmail());
        return new AuthResponse(token, user);
    }

    public User buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // CORREÇÃO: Usando os Getters gerados pelo Lombok
    public User atualizarPerfil(String id, UpdateProfileRequest request) {
        User user = buscarPorId(id);

        if (request.getNome() != null && !request.getNome().isBlank()) {
            user.setNome(request.getNome());
        }

        if (request.getTelefone() != null) {
            user.setTelefone(request.getTelefone());
        }

        if (request.getDataNascimento() != null) {
            user.setDataNascimento(request.getDataNascimento());
        }

        return repository.save(user);
    }

    public void alterarSenha(String id, ChangePasswordRequest request) {
        User user = buscarPorId(id);
        if (!passwordEncoder.matches(request.senhaAtual, user.getSenha()))
            throw new RuntimeException("Senha atual incorreta");
        user.setSenha(passwordEncoder.encode(request.novaSenha));
        repository.save(user);
    }
}