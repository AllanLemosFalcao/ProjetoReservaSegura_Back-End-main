package br.com.reservasegura.controller;

import br.com.reservasegura.dto.DepositRequest;
import br.com.reservasegura.entity.Transaction;
import br.com.reservasegura.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class TransactionController {

    private final TransactionService service;

    /** Depósito ou Saque — definido pelo campo "tipo" no body ("DEPOSITO" ou "SAQUE") */
    @PostMapping
    public ResponseEntity<Transaction> movimentar(@RequestBody DepositRequest request) {
        if (request.tipo != null) {
            request.tipo = request.tipo.toLowerCase();
        }
        return ResponseEntity.ok(service.movimentar(request));
    }

    /** Mantido para compatibilidade com o api.js anterior */
        @PostMapping("/deposito")
    public ResponseEntity<Transaction> depositar(@RequestBody DepositRequest request) {
        request.tipo = "deposito";
        return ResponseEntity.ok(service.movimentar(request));
    }


    @GetMapping
    public ResponseEntity<List<Transaction>> listar(@RequestParam String userId) {
        return ResponseEntity.ok(service.listarPorUsuario(userId));
    }

    @GetMapping("/meta/{goalId}")
    public ResponseEntity<List<Transaction>> listarPorMeta(@PathVariable String goalId) {
        return ResponseEntity.ok(service.listarPorMeta(goalId));
    }
}
