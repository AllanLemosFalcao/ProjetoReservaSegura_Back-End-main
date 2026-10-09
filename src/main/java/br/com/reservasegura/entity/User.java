package br.com.reservasegura.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "usuario") // <-- Apontando para a tabela correta no Railway
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

     @Column(unique = true)
    private String username;

    @Column(name = "senha_hash", nullable = false) // <-- Mapeado para a coluna 'senha_hash'
    private String senha;

    @Column(name = "data_nascimento") // <-- Mapeado para a coluna correta
    private LocalDate dataNascimento;

    // Gamificação
    @Column(name = "nivel_xp_total", nullable = false) // <-- Mapeado para 'nivel_xp_total'
    private Integer xpTotal = 0;

    @Column(name = "pontos_premio", nullable = false) // <-- Mapeado para 'pontos_premio'
    private Integer pontosPremio = 0;  // moedas

    // Campo adicional que existe na sua tabela antiga
    @Column(name = "liga_atual_id")
    private Integer ligaAtualId;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();
    

    // ------------------------------------------------------------------------
    // ⚠️ CAMPOS TRANSIENTES (Não existem no banco de dados)
    // A anotação @Transient impede que o Hibernate tente procurar/criar estas
    // colunas na tabela "usuario", evitando erros de inicialização.
    // ------------------------------------------------------------------------
    
    @Transient
    private String telefone;

    @Transient
    private Integer nivel = 1;

    @Transient
    private Integer sequenciaDias = 0;

    @Transient
    private Integer totalConquistas = 0;
}