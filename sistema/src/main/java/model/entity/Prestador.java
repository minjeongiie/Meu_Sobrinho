package model.entity;

public class Prestador extends Usuario {

    private String descricao;
    private double valorMedio;
    private boolean perfilPublico;
    private String portfolio;
    private String cpfCnpj;
    private String celular;
    private Categoria categoria;

    public Prestador() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorMedio() {
        return valorMedio;
    }

    public void setValorMedio(double valorMedio) {
        this.valorMedio = valorMedio;
    }

    public boolean isPerfilPublico() {
        return perfilPublico;
    }

    public void setPerfilPublico(boolean perfilPublico) {
        this.perfilPublico = perfilPublico;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}