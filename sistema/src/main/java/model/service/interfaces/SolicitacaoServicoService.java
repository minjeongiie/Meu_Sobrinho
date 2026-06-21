package model.service.interfaces;

import model.entity.Categoria;
import model.entity.SolicitacaoServico;

import java.time.LocalDate;
import java.util.List;

public interface SolicitacaoServicoService {

    void publicarSolicitacao(
            Long clienteId,
            String titulo,
            String descricao,
            Double valorEstimado,
            Categoria categoria,
            LocalDate dataDesejada
    );

    void fecharSolicitacao(Long solicitacaoId);

    SolicitacaoServico buscarPorId(Long id);

    List<SolicitacaoServico> listarTodas();

    List<SolicitacaoServico> listarAbertas();

    List<SolicitacaoServico> listarPorCliente(Long clienteId);
}