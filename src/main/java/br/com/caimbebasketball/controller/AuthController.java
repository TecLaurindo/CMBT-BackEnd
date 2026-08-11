package br.com.caimbebasketball.controller;

import br.com.caimbebasketball.config.JwtUtil;
import br.com.caimbebasketball.dto.LoginDTO;
import br.com.caimbebasketball.dto.TokenDTO;
import br.com.caimbebasketball.model.Usuario;
import br.com.caimbebasketball.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos.");
        }

        Usuario usuario = usuarioOpt.get();

        // Compara a senha informada com o hash salvo no banco
        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos.");
        }

        // Gera o token JWT
        String token = jwtUtil.gerarToken(usuario.getEmail(), usuario.getPerfil().name());

        return ResponseEntity.ok(new TokenDTO(token, usuario.getNome(), usuario.getPerfil().name()));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Erro: E-mail já cadastrado!");
        }

        // Criptografa a senha antes de salvar no PostgreSQL
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario novoUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}