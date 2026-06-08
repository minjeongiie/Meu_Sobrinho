package model.service.interfaces;

import model.entity.Contratacao;

import java.time.LocalDate;
import java.util.List;

public interface ContratacaoService {

    void solicitarContratacao(
            Long clienteId,
            Long prestadorId,
            String descricao,
            Double preco,
            LocalDate dataSolicitada
    );

    void aceitarContratacao(Long contratacaoId);

    void concluirContratacao(Long contratacaoId);

    void recusarContratacao(Long contratacaoId);

    void enviarContraproposta(
            Long contratacaoId,
            Double valorContraproposta,
            String mensagemContraproposta
    );

    Contratacao buscarPorId(Long id);

    List<Contratacao> listarPorCliente(Long clienteId);

    List<Contratacao> listarPorPrestador(Long prestadorId);
}