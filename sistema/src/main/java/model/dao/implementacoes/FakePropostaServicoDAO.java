package model.dao.implementacoes;

import model.dao.interfaces.PropostaServicoDAO;
import model.entity.PropostaServico;

import java.util.ArrayList;
import java.util.List;

public class FakePropostaServicoDAO implements PropostaServicoDAO {

    private static final List<PropostaServico> propostas =
            new ArrayList<>();

    private static Long proximoId = 1L;

    @Override
    public void salvar(PropostaServico proposta) {

        proposta.setId(proximoId++);

        propostas.add(proposta);
    }

    @Override
    public void atualizar(PropostaServico proposta) {

        for (int i = 0; i < propostas.size(); i++) {

            if (propostas.get(i).getId().equals(proposta.getId())) {
                propostas.set(i, proposta);
                return;
            }
        }
    }

    @Override
    public PropostaServico buscarPorId(Long id) {

        for (PropostaServico proposta : propostas) {

            if (proposta.getId().equals(id)) {
                return proposta;
            }
        }

        return null;
    }

    @Override
    public List<PropostaServico> listarPorSolicitacao(Long solicitacaoId) {

        List<PropostaServico> resultado = new ArrayList<>();

        for (PropostaServico proposta : propostas) {

            if (proposta.getSolicitacaoId().equals(solicitacaoId)) {
                resultado.add(proposta);
            }
        }

        return resultado;
    }

    @Override
    public List<PropostaServico> listarPorPrestador(Long prestadorId) {

        List<PropostaServico> resultado = new ArrayList<>();

        for (PropostaServico proposta : propostas) {

            if (proposta.getPrestadorId().equals(prestadorId)) {
                resultado.add(proposta);
            }
        }

        return resultado;
    }
}
