package br.com.reservasegura.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateProfileRequest {
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
}