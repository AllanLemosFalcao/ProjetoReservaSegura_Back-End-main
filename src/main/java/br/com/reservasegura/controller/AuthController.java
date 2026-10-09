package br.com.reservasegura.controller;

import br.com.reservasegura.dto.*;
import br.com.reservasegura.entity.User;
import br.com.reservasegura.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.registrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<User> buscar(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/usuario/{id}")
    public ResponseEntity<User> atualizar(@PathVariable String id,
                                          @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(service.atualizarPerfil(id, request));
    }

    @PostMapping("/usuario/{id}/senha")
    public ResponseEntity<Void> alterarSenha(@PathVariable String id,
                                              @RequestBody ChangePasswordRequest request) {
        service.alterarSenha(id, request);
        return ResponseEntity.noContent().build();
    }
}
