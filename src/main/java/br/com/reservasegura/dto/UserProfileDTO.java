package br.com.reservasegura.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String nome;
    private String titulo;
    private Integer nivel;
    private Integer xpAtual;
    private Integer xpProximoNivel;
    private Integer moedas;
    private Integer moedasAcumuladasMes;
    private Integer sequenciaAtual;
    private Integer recordeSequencia;
    private Integer totalConquistas;
    private String username;

    private Integer missoesCompletas;
    private Integer missoesTotal;
    private Double totalPoupado;
    private Double metaMensal;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}

