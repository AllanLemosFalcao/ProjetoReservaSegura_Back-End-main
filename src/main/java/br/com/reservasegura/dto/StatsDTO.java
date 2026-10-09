package br.com.reservasegura.dto;

import lombok.Data;

@Data
public class StatsDTO {
    private Integer totalConquistas;
    private Double guardadoEsteMes;
    private Integer sequenciaAtual;
}