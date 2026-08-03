package br.com.caimbebasketball.controller;

import br.com.caimbebasketball.model.Mensalidade;
import br.com.caimbebasketball.service.MensalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mensalidades")
@CrossOrigin(origins = "*")
public class MensalidadeController {

    @Autowired
    private MensalidadeService mensalidadeService;

    // Criar uma nova cobrança (POST http://localhost:8080/api/mensalidades)
    @PostMapping
    public ResponseEntity<Mensalidade> criar(@RequestBody Mensalidade mensalidade) {
        return ResponseEntity.ok(mensalidadeService.gerarMensalidade(mensalidade));
    }

    // Botão de Baixa Manual (PUT http://localhost:8080/api/mensalidades/1/pagar)
    @PutMapping("/{id}/pagar")
    public ResponseEntity<Mensalidade> confirmarPagamentoManual(@PathVariable Long id) {
        Mensalidade mensalidadePaga = mensalidadeService.registrarPagamento(id);
        return ResponseEntity.ok(mensalidadePaga);
    }

    // Buscar histórico de um aluno (GET http://localhost:8080/api/mensalidades/atleta/1)
    @GetMapping("/atleta/{atletaId}")
    public ResponseEntity<List<Mensalidade>> listarPorAtleta(@PathVariable Long atletaId) {
        return ResponseEntity.ok(mensalidadeService.listarPorAtleta(atletaId));
    }
}