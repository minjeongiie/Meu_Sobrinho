package model.entity;

import java.time.LocalDate;

public class Contratacao {

    private Long id;
    private Long clienteId;
    private Long prestadorId;
    private String descricao;
    private Double preco;
    private LocalDate dataSolicitada;
    private StatusContratacao status;

    private Double valorContraproposta;
    private String mensagemContraproposta;

    public Contratacao() {
        this.status = StatusContratacao.PENDENTE;
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

    public Long getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(Long prestadorId) {
        this.prestadorId = prestadorId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataSolicitada() {
        return dataSolicitada;
    }

    public void setDataSolicitada(LocalDate dataSolicitada) {
        this.dataSolicitada = dataSolicitada;
    }

    public StatusContratacao getStatus() {
        return status;
    }

    public void setStatus(StatusContratacao status) {
        this.status = status;
    }

    public Double getValorContraproposta() {
        return valorContraproposta;
    }

    public void setValorContraproposta(Double valorContraproposta) {
        this.valorContraproposta = valorContraproposta;
    }

    public String getMensagemContraproposta() {
        return mensagemContraproposta;
    }

    public void setMensagemContraproposta(String mensagemContraproposta) {
        this.mensagemContraproposta = mensagemContraproposta;
    }
}