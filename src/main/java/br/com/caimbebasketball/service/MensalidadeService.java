package br.com.caimbebasketball.service;

import br.com.caimbebasketball.model.Mensalidade;
import br.com.caimbebasketball.model.enums.StatusFinanceiro;
import br.com.caimbebasketball.repository.MensalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    public List<Mensalidade> listarPorAtleta(Long atletaId) {
        // No futuro podemos criar um método customizado no Repository para filtrar por ID do atleta
        return mensalidadeRepository.findAll();
    }

    public Mensalidade gerarMensalidade(Mensalidade mensalidade) {
        // Define o status inicial como PENDENTE ao gerar uma nova cobrança
        mensalidade.setPago(Boolean.FALSE);
        return mensalidadeRepository.save(mensalidade);
    }

    // Regra: Registrar o Pagamento da Mensalidade
    public Mensalidade registrarPagamento(Long id) {
        Mensalidade mensalidade = mensalidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensalidade não encontrada!"));


        if (Boolean.TRUE.equals(mensalidade.getPago())) { // Atualizado de getStatus para getPago
            throw new IllegalArgumentException("Esta mensalidade já consta como paga.");
        }

        mensalidade.setPago(Boolean.TRUE);
        mensalidade.setDataPagamento(LocalDate.now()); // Registra o dia de hoje como a data do pagamento

        return mensalidadeRepository.save(mensalidade);
    }
}