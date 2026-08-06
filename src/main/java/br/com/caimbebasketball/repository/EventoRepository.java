package br.com.caimbebasketball.repository;

import br.com.caimbebasketball.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Busca eventos por categoria (para o app do atleta filtrar seus treinos)
    List<Evento> findByCategoriaContainingIgnoreCase(String categoria);

    // Busca eventos a partir de uma data/hora (próximos compromissos)
    List<Evento> findByDataHoraInicioAfterOrderByDataHoraInicioAsc(LocalDateTime dataHora);
}