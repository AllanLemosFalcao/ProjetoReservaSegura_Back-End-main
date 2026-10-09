package br.com.reservasegura.repository;

import br.com.reservasegura.entity.MissionProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionProgressRepository extends JpaRepository<MissionProgress, String> {
    
    // Busca todas as missões vinculadas a um ID de usuário
    List<MissionProgress> findByUserId(String userId);
    long countByUserIdAndConcluidaTrue(String userId);
}