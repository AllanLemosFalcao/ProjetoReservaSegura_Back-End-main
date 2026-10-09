package br.com.reservasegura.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "meta_reserva") 
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 45)
    private String nome;

    @Column(name = "valor_alvo", nullable = false) // Traduz valorAlvo para valor_alvo
    private Double valorAlvo;

    @Column(name = "valor_atual", nullable = false) // Traduz valorAtual para valor_atual
    private Double valorAtual = 0.0;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    // O Veredito: OPÇÃO B! 
    // Mantemos o seu padrão original, apenas mudando a coluna para usuario_id
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private User user;

    // ------------------------------------------------------------------------
    // ⚠️ CAMPOS TRANSIENTES (Usados pelo Frontend)
    // A anotação @Transient impede que o Hibernate tente procurar/criar estas
    // colunas na tabela "meta_reserva", evitando erros ao iniciar a API.
    // ------------------------------------------------------------------------
    
    @Transient
    private String categoria;       // lazer, essencial, educacao, compras, etc.
    
    @Transient
    private Double metaMensal;      // quanto depositar por mês
    
    @Transient
    private Double rendimento = 0.0; // rendimentos acumulados
    
    @Transient
    private String icone;           // nome do ícone (ex: "Plane", "Shield")
    
    @Transient
    private LocalDate dataInicio;
}