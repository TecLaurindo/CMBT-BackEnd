package br.com.caimbebasketball.dto;

public class TokenDTO {
    private String token;
    private String nome;
    private String perfil;

    public TokenDTO(String token, String nome, String perfil) {
        this.token = token;
        this.nome = nome;
        this.perfil = perfil;
    }

    public String getToken() { return token; }
    public String getNome() { return nome; }
    public String getPerfil() { return perfil; }
}