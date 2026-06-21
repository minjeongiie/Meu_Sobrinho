package model.dao.interfaces;

import model.entity.PropostaServico;

import java.util.List;

public interface PropostaServicoDAO {

    void salvar(PropostaServico proposta);

    void atualizar(PropostaServico proposta);

    PropostaServico buscarPorId(Long id);

    List<PropostaServico> listarPorSolicitacao(Long solicitacaoId);

    List<PropostaServico> listarPorPrestador(Long prestadorId);
}