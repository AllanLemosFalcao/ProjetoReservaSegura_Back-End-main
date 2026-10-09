package br.com.reservasegura.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "progresso_missao")
public class MissionProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "missao_id", nullable = false)
    private Mission mission;

    @Column(name = "progresso_atual", nullable = false)
    private Integer progressoAtual = 0;

    @Column(name = "meta_progresso", nullable = false)
    private Integer metaProgresso = 1;

    @Column(nullable = false)
    private Boolean concluida = false;

    @Column(name = "concluida_em")
    private LocalDateTime concluidaEm;
}