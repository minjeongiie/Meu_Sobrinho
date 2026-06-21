package model.entity;

import java.time.LocalDate;

public class PropostaServico {

    private Long id;
    private Long solicitacaoId;
    private Long prestadorId;
    private Double valorProposto;
    private String descricao;
    private LocalDate prazoConclusao;
    private StatusPropostaServico status;

    public PropostaServico() {
        this.status = StatusPropostaServico.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(Long solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public Long getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(Long prestadorId) {
        this.prestadorId = prestadorId;
    }

    public Double getValorProposto() {
        return valorProposto;
    }

    public void setValorProposto(Double valorProposto) {
        this.valorProposto = valorProposto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazoConclusao() {
        return prazoConclusao;
    }

    public void setPrazoConclusao(LocalDate prazoConclusao) {
        this.prazoConclusao = prazoConclusao;
    }

    public StatusPropostaServico getStatus() {
        return status;
    }

    public void setStatus(StatusPropostaServico status) {
        this.status = status;
    }
}