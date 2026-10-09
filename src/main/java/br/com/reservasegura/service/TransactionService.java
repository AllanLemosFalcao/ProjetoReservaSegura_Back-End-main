package br.com.reservasegura.service;

import br.com.reservasegura.dto.DepositRequest;
import br.com.reservasegura.entity.Goal;
import br.com.reservasegura.entity.Transaction;
import br.com.reservasegura.entity.User;
import br.com.reservasegura.repository.GoalRepository;
import br.com.reservasegura.repository.TransactionRepository;
import br.com.reservasegura.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public Transaction movimentar(DepositRequest request) {
        Goal goal = goalRepository.findById(request.goalId)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));
        User user = userRepository.findById(request.userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String tipo = (request.tipo != null && request.tipo.equalsIgnoreCase("SAQUE")) ? "saque" : "deposito";

        if (tipo.equals("saque")) {
            if (goal.getValorAtual() < request.valor)
                throw new RuntimeException("Saldo insuficiente na caixinha");
            goal.setValorAtual(goal.getValorAtual() - request.valor);
        } else {
            goal.setValorAtual(goal.getValorAtual() + request.valor);
            // Ganha XP proporcional ao depósito
            int xpGanho = (int) (request.valor / 10);
            user.setXpTotal(user.getXpTotal() + xpGanho);
            // Ganha moedas (pontosPremio) proporcional
            int moedasGanhas = (int) (request.valor / 50);
            user.setPontosPremio(user.getPontosPremio() + moedasGanhas);
            userRepository.save(user);
        }
        goalRepository.save(goal);

        Transaction transaction = new Transaction();
        transaction.setValor(request.valor);
        transaction.setTipo(tipo);
        transaction.setGoal(goal);
        transaction.setUser(user);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> listarPorUsuario(String userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> listarPorMeta(String goalId) {
        return transactionRepository.findByGoalId(goalId);
    }
}
