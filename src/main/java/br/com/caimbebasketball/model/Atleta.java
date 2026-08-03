package br.com.caimbebasketball.model;

import br.com.caimbebasketball.model.enums.NivelBasquete;
import br.com.caimbebasketball.model.enums.StatusFinanceiro;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_atleta")
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 14)
    private String cpf;

    private String fotoPerfilUrl;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String categoria;
    private String posicao;

    // --- Saúde e Biometria ---
    private Double altura;
    private Double peso;

    @Column(name = "tipo_sanguineo", length = 3)
    private String tipoSanguineo;

    @Column(columnDefinition = "TEXT")
    private String observacoesMedicas;

    // --- Escolar e Técnico ---
    private String escola;
    private String serie;

    @Enumerated(EnumType.STRING)
    private NivelBasquete nivel;

    // --- Financeiro ---
    @Enumerated(EnumType.STRING)
    private StatusFinanceiro status;

    // --- Construtores ---
    public Atleta() {
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public String getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    public String getObservacoesMedicas() { return observacoesMedicas; }
    public void setObservacoesMedicas(String observacoesMedicas) { this.observacoesMedicas = observacoesMedicas; }

    public String getEscola() { return escola; }
    public void setEscola(String escola) { this.escola = escola; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public NivelBasquete getNivel() { return nivel; }
    public void setNivel(NivelBasquete nivel) { this.nivel = nivel; }

    public StatusFinanceiro getStatus() { return status; }
    public void setStatus(StatusFinanceiro status) { this.status = status; }

    public String getFotoPerfilUrl() { return fotoPerfilUrl; }
    public void setFotoPerfilUrl(String fotoPerfilUrl) { this.fotoPerfilUrl = fotoPerfilUrl; }
}