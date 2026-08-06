package br.com.caimbebasketball.model;

import br.com.caimbebasketball.model.enums.TipoEvento;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoEvento tipo;

    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    private LocalDateTime dataHoraFim;

    @Column(length = 100)
    private String local;

    @Column(length = 50)
    private String categoria; // Ex: "Sub-17", "Adulto", "Todas"

    @Column(columnDefinition = "TEXT")
    private String observacoes; // Ex: "Trazer uniforme escuro", "Adversário: time X"

    // --- Construtores ---
    public Evento() {
    }

    public Evento(String titulo, TipoEvento tipo, LocalDateTime dataHoraInicio, String local, String categoria) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataHoraInicio = dataHoraInicio;
        this.local = local;
        this.categoria = categoria;
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public TipoEvento getTipo() { return tipo; }
    public void setTipo(TipoEvento tipo) { this.tipo = tipo; }

    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }

    public LocalDateTime getDataHoraFim() { return dataHoraFim; }
    public void setDataHoraFim(LocalDateTime dataHoraFim) { this.dataHoraFim = dataHoraFim; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}