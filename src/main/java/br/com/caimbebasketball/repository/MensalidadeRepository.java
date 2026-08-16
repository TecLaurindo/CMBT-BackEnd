package br.com.caimbebasketball.repository;

import br.com.caimbebasketball.model.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long> {

    // 1. Método para buscar mensalidades pendentes
    List<Mensalidade> findByPagoFalseOrderByDataVencimentoAsc();

    // 2. Método para buscar por Mês e Ano usando JPQL
    @Query("SELECT m FROM Mensalidade m WHERE EXTRACT(MONTH FROM m.dataVencimento) = :mes AND EXTRACT(YEAR FROM m.dataVencimento) = :ano ORDER BY m.dataVencimento ASC")
    List<Mensalidade> buscarPorMesEAno(@Param("mes") int mes, @Param("ano") int ano);

    // 3. Somas para o Dashboard
    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Mensalidade m WHERE m.pago = true")
    BigDecimal somarMensalidadesPagas();

    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Mensalidade m WHERE m.pago = false OR m.pago IS NULL")
    BigDecimal somarMensalidadesPendentes();
}