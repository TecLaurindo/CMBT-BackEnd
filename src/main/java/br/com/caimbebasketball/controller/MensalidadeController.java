package br.com.caimbebasketball.controller;

import br.com.caimbebasketball.model.Mensalidade;
import br.com.caimbebasketball.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mensalidades")
@CrossOrigin(origins = "*")
public class MensalidadeController {

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    // Retorna pendências por padrão ao carregar
    @GetMapping("/pendentes")
    public ResponseEntity<List<Mensalidade>> listarPendentes() {
        return ResponseEntity.ok(mensalidadeRepository.findByPagoFalseOrderByDataVencimentoAsc());
    }

    // Filtro por Mês X e Ano Y
    @GetMapping("/filtrar")
    public ResponseEntity<List<Mensalidade>> filtrarPorMesEAno(
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano) {

        int mesBusca = (mes != null) ? mes : LocalDate.now().getMonthValue();
        int anoBusca = (ano != null) ? ano : LocalDate.now().getYear();

        return ResponseEntity.ok(mensalidadeRepository.buscarPorMesEAno(mesBusca, anoBusca));
    }

    // Dar baixa / Pagar mensalidade
    @PutMapping("/{id}/pagar")
    public ResponseEntity<Mensalidade> darBaixa(@PathVariable Long id) {
        return mensalidadeRepository.findById(id).map(m -> {
            m.setPago(true);
            return ResponseEntity.ok(mensalidadeRepository.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }
}