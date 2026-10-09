package br.com.reservasegura.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "movimentacoes")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false, length = 45)
    private String tipo; // Atenção: o banco só aceita as palavras 'deposito' ou 'saque'

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "meta_reserva_id") // <-- Alterado de 'goal_id' para 'meta_reserva_id'
    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // <-- Alterado de 'user_id' para 'usuario_id'
    private User user;
    
    @Column(nullable = false, length = 45)
    private String status = "pendente"; // O banco aceita: 'pendente', 'concluida' ou 'cancelada'

    @Column(name = "processado_em")
    private LocalDateTime processadoEm;
}