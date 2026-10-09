package br.com.reservasegura.controller;

import br.com.reservasegura.dto.StatsDTO;
import br.com.reservasegura.repository.MissionProgressRepository;
import br.com.reservasegura.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatisticas")
@CrossOrigin(origins = "*")
public class StatsController {

    @Autowired
    private TransactionRepository txRepo;

    @Autowired
    private MissionProgressRepository missionRepo;

    @GetMapping("/{userId}")
    public ResponseEntity<StatsDTO> getStats(@PathVariable String userId) {
        StatsDTO stats = new StatsDTO();
        
        // 1. Puxa a soma dos depósitos deste mês do banco
        stats.setGuardadoEsteMes(txRepo.sumDepositosMesAtual(userId));
        
        // 2. Conta quantas missões têm concluida = true
        stats.setTotalConquistas((int) missionRepo.countByUserIdAndConcluidaTrue(userId));
        
        // 3. Sequência de dias (ficará fixo em 1 até criarmos a tabela de histórico de logins)
        stats.setSequenciaAtual(1); 
        
        return ResponseEntity.ok(stats);
    }
}