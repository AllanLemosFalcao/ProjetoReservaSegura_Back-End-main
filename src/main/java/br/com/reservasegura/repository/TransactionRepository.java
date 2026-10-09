package br.com.reservasegura.repository;

import br.com.reservasegura.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
    List<Transaction> findByUserId(String userId);
    List<Transaction> findByGoalId(String goalId);
    // Soma todos os depósitos feitos no mês e ano atuais
    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transaction t WHERE t.user.id = :userId AND t.tipo = 'deposito' AND EXTRACT(MONTH FROM t.criadoEm) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM t.criadoEm) = EXTRACT(YEAR FROM CURRENT_DATE)")
    Double sumDepositosMesAtual(@Param("userId") String userId);
}