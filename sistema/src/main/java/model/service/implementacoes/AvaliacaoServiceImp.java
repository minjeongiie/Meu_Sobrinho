package model.service.implementacoes;

import model.dao.implementacoes.FakeAvaliacaoDAO;
import model.dao.implementacoes.FakeContratacaoDAO;
import model.dao.interfaces.AvaliacaoDAO;
import model.dao.interfaces.ContratacaoDAO;
import model.entity.Avaliacao;
import model.entity.Contratacao;
import model.entity.StatusContratacao;
import model.service.interfaces.AvaliacaoService;

import java.util.List;

public class AvaliacaoServiceImp implements AvaliacaoService {

    private final AvaliacaoDAO avaliacaoDAO;
    private final ContratacaoDAO contratacaoDAO;

    public AvaliacaoServiceImp() {
        this.avaliacaoDAO = new FakeAvaliacaoDAO();
        this.contratacaoDAO = new FakeContratacaoDAO();
    }

    @Override
    public void avaliarContratacao(
            Long contratacaoId,
            Long clienteId,
            Long prestadorId,
            int nota,
            String comentario
    ) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException(
                    "A nota deve ser entre 1 e 5."
            );
        }

        Contratacao contratacao =
                contratacaoDAO.buscarPorId(contratacaoId);

        if (contratacao == null) {
            throw new IllegalArgumentException(
                    "Contratação não encontrada."
            );
        }

        if (contratacao.getStatus() != StatusContratacao.CONCLUIDA) {
            throw new IllegalArgumentException(
                    "Apenas contratações concluídas podem ser avaliadas."
            );
        }

        if (!contratacao.getClienteId().equals(clienteId)) {
            throw new IllegalArgumentException(
                    "Apenas o cliente responsável pela contratação pode avaliar."
            );
        }

        if (!contratacao.getPrestadorId().equals(prestadorId)) {
            throw new IllegalArgumentException(
                    "Prestador inválido para esta contratação."
            );
        }

        Avaliacao avaliacaoExistente =
                avaliacaoDAO.buscarPorContratacao(contratacaoId);

        if (avaliacaoExistente != null) {
            throw new IllegalArgumentException(
                    "Esta contratação já foi avaliada."
            );
        }

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setContratacaoId(contratacaoId);
        avaliacao.setClienteId(clienteId);
        avaliacao.setPrestadorId(prestadorId);
        avaliacao.setNota(nota);
        avaliacao.setComentario(comentario);

        avaliacaoDAO.salvar(avaliacao);
    }

    @Override
    public double calcularMediaPorPrestador(Long prestadorId) {

        List<Avaliacao> avaliacoes =
                avaliacaoDAO.listarPorPrestador(prestadorId);

        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        int soma = 0;

        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getNota();
        }

        return (double) soma / avaliacoes.size();
    }

    @Override
    public int contarAvaliacoesPorPrestador(Long prestadorId) {
        return avaliacaoDAO.listarPorPrestador(prestadorId).size();
    }

    @Override
    public Avaliacao buscarPorContratacao(Long contratacaoId) {
        return avaliacaoDAO.buscarPorContratacao(contratacaoId);
    }

    @Override
    public List<Avaliacao> listarPorPrestador(Long prestadorId) {
        return avaliacaoDAO.listarPorPrestador(prestadorId);
    }

    @Override
    public List<Avaliacao> listarPorCliente(Long clienteId) {
        return avaliacaoDAO.listarPorCliente(clienteId);
    }
}