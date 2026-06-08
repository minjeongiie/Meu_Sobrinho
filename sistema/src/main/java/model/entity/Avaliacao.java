package model.entity;

public class Avaliacao {

    private Long id;
    private Long contratacaoId;
    private Long clienteId;
    private Long prestadorId;
    private int nota;
    private String comentario;

    public Avaliacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContratacaoId() {
        return contratacaoId;
    }

    public void setContratacaoId(Long contratacaoId) {
        this.contratacaoId = contratacaoId;
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

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}