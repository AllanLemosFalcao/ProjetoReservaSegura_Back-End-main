package br.com.reservasegura.repository;

import br.com.reservasegura.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, String> {
    List<Goal> findByUserId(String userId);
}
