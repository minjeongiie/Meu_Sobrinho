package model.dao.implementacoes;

import model.dao.interfaces.SolicitacaoServicoDAO;
import model.entity.SolicitacaoServico;
import model.entity.StatusSolicitacaoServico;

import java.util.ArrayList;
import java.util.List;

public class FakeSolicitacaoServicoDAO implements SolicitacaoServicoDAO {

    private static final List<SolicitacaoServico> solicitacoes =
            new ArrayList<>();

    private static Long proximoId = 1L;

    @Override
    public void salvar(SolicitacaoServico solicitacao) {

        solicitacao.setId(proximoId++);

        solicitacoes.add(solicitacao);
    }

    @Override
    public void atualizar(SolicitacaoServico solicitacao) {

        for (int i = 0; i < solicitacoes.size(); i++) {

            if (solicitacoes.get(i).getId().equals(solicitacao.getId())) {
                solicitacoes.set(i, solicitacao);
                return;
            }
        }
    }

    @Override
    public SolicitacaoServico buscarPorId(Long id) {

        for (SolicitacaoServico solicitacao : solicitacoes) {

            if (solicitacao.getId().equals(id)) {
                return solicitacao;
            }
        }

        return null;
    }

    @Override
    public List<SolicitacaoServico> listarTodas() {

        return new ArrayList<>(solicitacoes);
    }

    @Override
    public List<SolicitacaoServico> listarAbertas() {

        List<SolicitacaoServico> abertas = new ArrayList<>();

        for (SolicitacaoServico solicitacao : solicitacoes) {

            if (solicitacao.getStatus() == StatusSolicitacaoServico.ABERTA) {
                abertas.add(solicitacao);
            }
        }

        return abertas;
    }

    @Override
    public List<SolicitacaoServico> listarPorCliente(Long clienteId) {

        List<SolicitacaoServico> resultado = new ArrayList<>();

        for (SolicitacaoServico solicitacao : solicitacoes) {

            if (solicitacao.getClienteId().equals(clienteId)) {
                resultado.add(solicitacao);
            }
        }

        return resultado;
    }
}