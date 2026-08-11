package br.com.caimbebasketball.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Chave secreta para assinar os tokens (em produção, pode ir para o application.properties)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Tempo de expiração do token (Ex: 10 horas em milissegundos)
    private static final long EXPIRATION_TIME = 36000000;

    // Gerar Token a partir do e-mail e do perfil do usuário
    public String gerarToken(String email, String perfil) {
        return Jwts.builder()
                .setSubject(email)
                .claim("perfil", perfil)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Extrair o e-mail (subject) de dentro do token
    public String obterEmailDoToken(String token) {
        return obterClaims(token).getSubject();
    }

    // Validar se o token é válido e não expirou
    public boolean validarToken(String token) {
        try {
            Claims claims = obterClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims obterClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}