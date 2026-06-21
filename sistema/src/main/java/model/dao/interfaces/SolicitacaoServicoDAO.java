package model.dao.interfaces;

import model.entity.SolicitacaoServico;

import java.util.List;

public interface SolicitacaoServicoDAO {

    void salvar(SolicitacaoServico solicitacao);

    void atualizar(SolicitacaoServico solicitacao);

    SolicitacaoServico buscarPorId(Long id);

    List<SolicitacaoServico> listarTodas();

    List<SolicitacaoServico> listarAbertas();

    List<SolicitacaoServico> listarPorCliente(Long clienteId);
}