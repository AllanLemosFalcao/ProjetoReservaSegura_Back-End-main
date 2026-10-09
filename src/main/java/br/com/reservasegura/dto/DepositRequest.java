package br.com.reservasegura.dto;

public class DepositRequest {
    public String goalId;
    public String userId;
    public Double valor;
    public String tipo; // "DEPOSITO" ou "SAQUE" — opcional, padrão DEPOSITO
}
