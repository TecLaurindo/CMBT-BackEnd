package br.com.caimbebasketball.controller;

import br.com.caimbebasketball.dto.DashboardResumoDTO;
import br.com.caimbebasketball.repository.AtletaRepository;
import br.com.caimbebasketball.repository.EventoRepository;
import br.com.caimbebasketball.repository.ItemEstoqueRepository;
import br.com.caimbebasketball.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private AtletaRepository atletaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @GetMapping("/resumo")
    public ResponseEntity<DashboardResumoDTO> obterResumo() {
        long atletasAtivos = atletaRepository.count();
        long eventos = eventoRepository.count();
        long estoque = itemEstoqueRepository.count();

        // Consulta direta no banco de dados
        BigDecimal valorPago = mensalidadeRepository.somarMensalidadesPagas();
        BigDecimal valorPendente = mensalidadeRepository.somarMensalidadesPendentes();

        DashboardResumoDTO resumo = new DashboardResumoDTO(
                atletasAtivos,
                eventos,
                estoque,
                valorPago,
                valorPendente
        );

        return ResponseEntity.ok(resumo);
    }
}