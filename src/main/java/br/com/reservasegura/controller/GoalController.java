package br.com.reservasegura.controller;

import br.com.reservasegura.dto.GoalRequest;
import br.com.reservasegura.entity.Goal;
import br.com.reservasegura.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metas")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService service;

    @PostMapping
    public ResponseEntity<Goal> criar(@RequestBody GoalRequest request) {
        return ResponseEntity.ok(service.criarMeta(request));
    }

    @GetMapping
    public ResponseEntity<List<Goal>> listar(@RequestParam String userId) {
        return ResponseEntity.ok(service.listarPorUsuario(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> buscar(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
