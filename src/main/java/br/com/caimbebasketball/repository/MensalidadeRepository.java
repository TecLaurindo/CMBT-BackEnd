package br.com.caimbebasketball.repository;

import br.com.caimbebasketball.model.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long> {

    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Mensalidade m WHERE m.pago = true")
    BigDecimal somarMensalidadesPagas();

    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Mensalidade m WHERE m.pago = false OR m.pago IS NULL")
    BigDecimal somarMensalidadesPendentes();
}