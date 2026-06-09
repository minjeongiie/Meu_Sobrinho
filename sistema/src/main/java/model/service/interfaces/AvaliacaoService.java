package model.service.interfaces;

import model.entity.Avaliacao;

import java.util.List;

public interface AvaliacaoService {

    void avaliarContratacao(
            Long contratacaoId,
            Long clienteId,
            Long prestadorId,
            int nota,
            String comentario
    );

    double calcularMediaPorPrestador(Long prestadorId);

    int contarAvaliacoesPorPrestador(Long prestadorId);

    Avaliacao buscarPorContratacao(Long contratacaoId);

    List<Avaliacao> listarPorPrestador(Long prestadorId);

    List<Avaliacao> listarPorCliente(Long clienteId);
}