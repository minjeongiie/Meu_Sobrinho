package model.service.implementacoes;

import model.dao.implementacoes.FakePropostaServicoDAO;
import model.dao.implementacoes.FakeSolicitacaoServicoDAO;
import model.dao.interfaces.PropostaServicoDAO;
import model.dao.interfaces.SolicitacaoServicoDAO;
import model.entity.PropostaServico;
import model.entity.SolicitacaoServico;
import model.entity.StatusPropostaServico;
import model.entity.StatusSolicitacaoServico;
import model.service.interfaces.PropostaServicoService;

import java.time.LocalDate;
import java.util.List;

public class PropostaServicoServiceImp
        implements PropostaServicoService {

    private final PropostaServicoDAO propostaDAO;
    private final SolicitacaoServicoDAO solicitacaoDAO;

    public PropostaServicoServiceImp() {
        this.propostaDAO = new FakePropostaServicoDAO();
        this.solicitacaoDAO = new FakeSolicitacaoServicoDAO();
    }

    @Override
    public void enviarProposta(
            Long solicitacaoId,
            Long prestadorId,
            Double valorProposto,
            String descricao,
            LocalDate prazoConclusao
    ) {

        SolicitacaoServico solicitacao =
                solicitacaoDAO.buscarPorId(solicitacaoId);

        if (solicitacao == null) {
            throw new IllegalArgumentException(
                    "Solicitação não encontrada."
            );
        }

        if (solicitacao.getStatus()
                == StatusSolicitacaoServico.FECHADA) {

            throw new IllegalArgumentException(
                    "Solicitação fechada não recebe propostas."
            );
        }

        if (prestadorId == null) {
            throw new IllegalArgumentException(
                    "Prestador obrigatório."
            );
        }

        if (valorProposto == null || valorProposto <= 0) {
            throw new IllegalArgumentException(
                    "Valor proposto inválido."
            );
        }

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Descrição obrigatória."
            );
        }

        if (prazoConclusao == null) {
            throw new IllegalArgumentException(
                    "Prazo de conclusão obrigatório."
            );
        }

        PropostaServico proposta = new PropostaServico();

        proposta.setSolicitacaoId(solicitacaoId);
        proposta.setPrestadorId(prestadorId);
        proposta.setValorProposto(valorProposto);
        proposta.setDescricao(descricao);
        proposta.setPrazoConclusao(prazoConclusao);
        proposta.setStatus(StatusPropostaServico.PENDENTE);

        propostaDAO.salvar(proposta);
    }

    @Override
    public void aceitarProposta(Long propostaId) {

        PropostaServico propostaAceita =
                propostaDAO.buscarPorId(propostaId);

        if (propostaAceita == null) {
            throw new IllegalArgumentException(
                    "Proposta não encontrada."
            );
        }

        SolicitacaoServico solicitacao =
                solicitacaoDAO.buscarPorId(
                        propostaAceita.getSolicitacaoId()
                );

        if (solicitacao == null) {
            throw new IllegalArgumentException(
                    "Solicitação não encontrada."
            );
        }

        if (solicitacao.getStatus()
                == StatusSolicitacaoServico.FECHADA) {

            throw new IllegalArgumentException(
                    "Esta solicitação já está fechada."
            );
        }

        propostaAceita.setStatus(StatusPropostaServico.ACEITA);
        propostaDAO.atualizar(propostaAceita);

        List<PropostaServico> propostas =
                propostaDAO.listarPorSolicitacao(
                        propostaAceita.getSolicitacaoId()
                );

        for (PropostaServico proposta : propostas) {

            if (!proposta.getId().equals(propostaAceita.getId())) {
                proposta.setStatus(StatusPropostaServico.RECUSADA);
                propostaDAO.atualizar(proposta);
            }
        }

        solicitacao.setStatus(StatusSolicitacaoServico.FECHADA);
        solicitacaoDAO.atualizar(solicitacao);
    }

    @Override
    public void recusarProposta(Long propostaId) {

        PropostaServico proposta =
                propostaDAO.buscarPorId(propostaId);

        if (proposta == null) {
            throw new IllegalArgumentException(
                    "Proposta não encontrada."
            );
        }

        proposta.setStatus(StatusPropostaServico.RECUSADA);

        propostaDAO.atualizar(proposta);
    }

    @Override
    public PropostaServico buscarPorId(Long id) {

        return propostaDAO.buscarPorId(id);
    }

    @Override
    public List<PropostaServico> listarPorSolicitacao(Long solicitacaoId) {

        return propostaDAO.listarPorSolicitacao(solicitacaoId);
    }

    @Override
    public List<PropostaServico> listarPorPrestador(Long prestadorId) {

        return propostaDAO.listarPorPrestador(prestadorId);
    }
}