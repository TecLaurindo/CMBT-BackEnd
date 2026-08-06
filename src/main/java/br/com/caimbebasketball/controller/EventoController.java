package br.com.caimbebasketball.controller;

import br.com.caimbebasketball.model.Evento;
import br.com.caimbebasketball.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    // Listar todos os eventos
    @GetMapping
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    // Listar próximos eventos (a partir de agora)
    @GetMapping("/proximos")
    public List<Evento> listarProximos() {
        return eventoRepository.findByDataHoraInicioAfterOrderByDataHoraInicioAsc(LocalDateTime.now());
    }

    // Listar por categoria (Ex: /api/eventos/categoria/Sub-17)
    @GetMapping("/categoria/{categoria}")
    public List<Evento> listarPorCategoria(@PathVariable String categoria) {
        return eventoRepository.findByCategoriaContainingIgnoreCase(categoria);
    }

    // Cadastrar novo treino/jogo (usado pelo Técnico ou Dono)
    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        Evento novoEvento = eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }

    // Cancelar/Remover evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        if (!eventoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}