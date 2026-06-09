package model.dao.interfaces;

import model.entity.Contratacao;

import java.util.List;

public interface ContratacaoDAO {

    void salvar(Contratacao contratacao);

    void atualizar(Contratacao contratacao);

    Contratacao buscarPorId(Long id);

    List<Contratacao> listarPorCliente(Long clienteId);

    List<Contratacao> listarPorPrestador(Long prestadorId);
}