package model.dao.interfaces;

import model.entity.Avaliacao;

import java.util.List;

public interface AvaliacaoDAO {

    void salvar(Avaliacao avaliacao);

    Avaliacao buscarPorId(Long id);

    Avaliacao buscarPorContratacao(Long contratacaoId);

    List<Avaliacao> listarPorPrestador(Long prestadorId);

    List<Avaliacao> listarPorCliente(Long clienteId);
}