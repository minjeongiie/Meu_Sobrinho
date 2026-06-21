package model.entity;

import java.time.LocalDate;

public class SolicitacaoServico {

    private Long id;
    private Long clienteId;
    private String titulo;
    private String descricao;
    private Double valorEstimado;
    private Categoria categoria;
    private LocalDate dataDesejada;
    private StatusSolicitacaoServico status;

    public SolicitacaoServico() {
        this.status = StatusSolicitacaoServico.ABERTA;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(Double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataDesejada() {
        return dataDesejada;
    }

    public void setDataDesejada(LocalDate dataDesejada) {
        this.dataDesejada = dataDesejada;
    }

    public StatusSolicitacaoServico getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacaoServico status) {
        this.status = status;
    }
}