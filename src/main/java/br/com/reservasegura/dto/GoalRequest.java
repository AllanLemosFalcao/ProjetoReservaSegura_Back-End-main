package br.com.reservasegura.dto;

import java.time.LocalDate;

public class GoalRequest {
    public String userId;
    public String nome;
    public Double valorAlvo;

    // Campos opcionais do novo frontend
    public String categoria;
    public Double metaMensal;
    public String icone;
    public LocalDate dataInicio;
}
