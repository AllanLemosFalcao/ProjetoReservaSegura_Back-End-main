package br.com.reservasegura.dto;

public class AuthResponse {
    public String token;
    public String userId;
    public String nome;
    public String email;
    public Integer xpTotal;
    public Integer pontosPremio;  // moedas
    public Integer nivel;
    public Integer sequenciaDias;
    public Integer totalConquistas;

    public AuthResponse(String token, br.com.reservasegura.entity.User user) {
        this.token = token;
        this.userId = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.xpTotal = user.getXpTotal();
        this.pontosPremio = user.getPontosPremio();
        this.nivel = user.getNivel();
        this.sequenciaDias = user.getSequenciaDias();
        this.totalConquistas = user.getTotalConquistas();
    }
}
