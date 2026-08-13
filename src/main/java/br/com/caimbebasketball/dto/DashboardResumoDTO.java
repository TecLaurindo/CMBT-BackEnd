package br.com.caimbebasketball.dto;

import java.math.BigDecimal;

public class DashboardResumoDTO {

    private long totalAtletasAtivos;
    private long totalEventosProximos;
    private long totalItensEstoque;
    private BigDecimal valorMensalidadesPagas;
    private BigDecimal valorMensalidadesPendentes;

    public DashboardResumoDTO(long totalAtletasAtivos, long totalEventosProximos,
                              long totalItensEstoque, BigDecimal valorMensalidadesPagas,
                              BigDecimal valorMensalidadesPendentes) {
        this.totalAtletasAtivos = totalAtletasAtivos;
        this.totalEventosProximos = totalEventosProximos;
        this.totalItensEstoque = totalItensEstoque;
        this.valorMensalidadesPagas = valorMensalidadesPagas;
        this.valorMensalidadesPendentes = valorMensalidadesPendentes;
    }

    public long getTotalAtletasAtivos() { return totalAtletasAtivos; }
    public long getTotalEventosProximos() { return totalEventosProximos; }
    public long getTotalItensEstoque() { return totalItensEstoque; }
    public BigDecimal getValorMensalidadesPagas() { return valorMensalidadesPagas; }
    public BigDecimal getValorMensalidadesPendentes() { return valorMensalidadesPendentes; }
}