package model.service.implementacoes;

import model.dao.implementacoes.FakeContratacaoDAO;
import model.dao.interfaces.ContratacaoDAO;
import model.entity.Contratacao;
import model.entity.StatusContratacao;
import model.service.interfaces.ContratacaoService;

import java.time.LocalDate;
import java.util.List;

public class ContratacaoServiceImp implements ContratacaoService {

    private final ContratacaoDAO contratacaoDAO;

    public ContratacaoServiceImp() {
        this.contratacaoDAO = new FakeContratacaoDAO();
    }

    @Override
    public void solicitarContratacao(
            Long clienteId,
            Long prestadorId,
            String descricao,
            Double preco,
            LocalDate dataSolicitada
    ) {
        Contratacao contratacao = new Contratacao();

        contratacao.setClienteId(clienteId);
        contratacao.setPrestadorId(prestadorId);
        contratacao.setDescricao(descricao);
        contratacao.setPreco(preco);
        contratacao.setDataSolicitada(dataSolicitada);
        contratacao.setStatus(StatusContratacao.PENDENTE);

        contratacaoDAO.salvar(contratacao);
    }

    @Override
    public void aceitarContratacao(Long contratacaoId) {

        Contratacao contratacao =
                contratacaoDAO.buscarPorId(contratacaoId);

        if (contratacao == null) {
            return;
        }

        if (contratacao.getStatus() != StatusContratacao.PENDENTE
                && contratacao.getStatus() != StatusContratacao.CONTRAPROPOSTA) {
            return;
        }

        contratacao.setStatus(StatusContratacao.ACEITA);

        contratacaoDAO.atualizar(contratacao);
    }

    @Override
    public void concluirContratacao(Long contratacaoId) {

        Contratacao contratacao =
                contratacaoDAO.buscarPorId(contratacaoId);

        if (contratacao == null) {
            return;
        }

        if (contratacao.getStatus() != StatusContratacao.ACEITA) {
            return;
        }

        contratacao.setStatus(StatusContratacao.CONCLUIDA);

        contratacaoDAO.atualizar(contratacao);
    }

    @Override
    public void recusarContratacao(Long contratacaoId) {

        Contratacao contratacao =
                contratacaoDAO.buscarPorId(contratacaoId);

        if (contratacao == null) {
            return;
        }

        if (contratacao.getStatus() != StatusContratacao.PENDENTE
                && contratacao.getStatus() != StatusContratacao.CONTRAPROPOSTA) {
            return;
        }

        contratacao.setStatus(StatusContratacao.RECUSADA);

        contratacaoDAO.atualizar(contratacao);
    }

    @Override
    public void enviarContraproposta(
            Long contratacaoId,
            Double valorContraproposta,
            String mensagemContraproposta
    ) {

        Contratacao contratacao =
                contratacaoDAO.buscarPorId(contratacaoId);

        if (contratacao == null) {
            return;
        }

        if (contratacao.getStatus() != StatusContratacao.PENDENTE) {
            return;
        }

        contratacao.setValorContraproposta(valorContraproposta);
        contratacao.setMensagemContraproposta(mensagemContraproposta);
        contratacao.setStatus(StatusContratacao.CONTRAPROPOSTA);

        contratacaoDAO.atualizar(contratacao);
    }

    @Override
    public Contratacao buscarPorId(Long id) {
        return contratacaoDAO.buscarPorId(id);
    }

    @Override
    public List<Contratacao> listarPorCliente(Long clienteId) {
        return contratacaoDAO.listarPorCliente(clienteId);
    }

    @Override
    public List<Contratacao> listarPorPrestador(Long prestadorId) {
        return contratacaoDAO.listarPorPrestador(prestadorId);
    }
}