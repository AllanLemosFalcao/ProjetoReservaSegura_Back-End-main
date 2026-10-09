package br.com.reservasegura.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "Nome obrigatório")
    public String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email obrigatório")
    public String email;

    @NotBlank(message = "CPF obrigatório")
    public String cpf;

    @NotBlank(message = "Senha obrigatória")
    public String senha;
}
