package br.com.reservasegura.controller;

import br.com.reservasegura.dto.MissionDTO;
import br.com.reservasegura.entity.MissionProgress;
import br.com.reservasegura.repository.MissionProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/missoes")
@CrossOrigin(origins = "*") // Permite que o React aceda
public class MissionController {

    @Autowired
    private MissionProgressRepository repository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<MissionDTO>> getMissionsByUser(@PathVariable String userId) {
        
        List<MissionProgress> progressos = repository.findByUserId(userId);
        
        // Converte as entidades do banco para o formato amigável do React
        List<MissionDTO> response = progressos.stream().map(progresso -> {
            MissionDTO dto = new MissionDTO();
            dto.setTitle(progresso.getMission().getTitulo());
            dto.setProgress(progresso.getProgressoAtual());
            dto.setTotal(progresso.getMetaProgresso());
            // O front-end exibe moedas, então enviamos 'pontosPremio' como recompensa
            dto.setReward(progresso.getMission().getPontosPremio());
            dto.setCompleted(progresso.getConcluida());
            dto.setType(progresso.getMission().getTipo()); 
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}