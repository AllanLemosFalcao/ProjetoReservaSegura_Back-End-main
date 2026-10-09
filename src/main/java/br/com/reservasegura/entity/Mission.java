package br.com.reservasegura.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "missao")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String titulo;

    private String descricao;

    private String tipo;

    @Column(name = "xp_recompensa", nullable = false)
    private Integer xpRecompensa;

    @Column(name = "pontos_premio", nullable = false)
    private Integer pontosPremio;

    @Column(nullable = false)
    private Boolean ativa = true;

    private String periodo;
}