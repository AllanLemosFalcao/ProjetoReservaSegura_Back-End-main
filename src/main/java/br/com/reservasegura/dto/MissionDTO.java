package br.com.reservasegura.dto;

import lombok.Data;

@Data
public class MissionDTO {
    private String title;
    private Integer progress;
    private Integer total;
    private Integer reward;
    private Boolean completed;
    private String type; // Enviaremos o tipo para o React escolher o ícone (ex: 'deposito', 'disciplina')
}