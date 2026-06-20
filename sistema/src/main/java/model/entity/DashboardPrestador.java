package model.entity;

public class DashboardPrestador {

    private int totalSolicitacoes;
    private int solicitacoesPendentes;
    private int solicitacoesAceitas;
    private int solicitacoesRecusadas;
    private int servicosConcluidos;
    private int totalAvaliacoes;
    private double notaMedia;

    public double getTaxaConclusao() {
        return taxaConclusao;
    }

    public void setTaxaConclusao(double taxaConclusao) {
        this.taxaConclusao = taxaConclusao;
    }

    public double getTaxaAceitacao() {
        return taxaAceitacao;
    }

    public void setTaxaAceitacao(double taxaAceitacao) {
        this.taxaAceitacao = taxaAceitacao;
    }

    private double taxaAceitacao;
    private double taxaConclusao;

    public DashboardPrestador() {
    }

    public int getTotalSolicitacoes() {
        return totalSolicitacoes;
    }

    public void setTotalSolicitacoes(int totalSolicitacoes) {
        this.totalSolicitacoes = totalSolicitacoes;
    }

    public int getSolicitacoesPendentes() {
        return solicitacoesPendentes;
    }

    public void setSolicitacoesPendentes(int solicitacoesPendentes) {
        this.solicitacoesPendentes = solicitacoesPendentes;
    }

    public int getSolicitacoesAceitas() {
        return solicitacoesAceitas;
    }

    public void setSolicitacoesAceitas(int solicitacoesAceitas) {
        this.solicitacoesAceitas = solicitacoesAceitas;
    }

    public int getSolicitacoesRecusadas() {
        return solicitacoesRecusadas;
    }

    public void setSolicitacoesRecusadas(int solicitacoesRecusadas) {
        this.solicitacoesRecusadas = solicitacoesRecusadas;
    }

    public int getServicosConcluidos() {
        return servicosConcluidos;
    }

    public void setServicosConcluidos(int servicosConcluidos) {
        this.servicosConcluidos = servicosConcluidos;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }
}