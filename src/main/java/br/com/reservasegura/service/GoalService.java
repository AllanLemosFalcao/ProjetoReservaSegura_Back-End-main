package br.com.reservasegura.service;

import br.com.reservasegura.dto.GoalRequest;
import br.com.reservasegura.entity.Goal;
import br.com.reservasegura.entity.User;
import br.com.reservasegura.repository.GoalRepository;
import br.com.reservasegura.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public Goal criarMeta(GoalRequest request) {
        User user = userRepository.findById(request.userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Goal goal = new Goal();
        goal.setNome(request.nome);
        goal.setValorAlvo(request.valorAlvo);
        goal.setCategoria(request.categoria);
        goal.setMetaMensal(request.metaMensal);
        goal.setIcone(request.icone);
        goal.setDataInicio(request.dataInicio != null ? request.dataInicio : LocalDate.now());
        goal.setUser(user);

        return goalRepository.save(goal);
    }

    public List<Goal> listarPorUsuario(String userId) {
        return goalRepository.findByUserId(userId);
    }

    public Goal buscarPorId(String id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));
    }

    public void deletar(String id) {
        goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));
        goalRepository.deleteById(id);
    }
}
