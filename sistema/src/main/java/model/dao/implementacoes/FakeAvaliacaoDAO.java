package model.dao.implementacoes;

import model.dao.interfaces.AvaliacaoDAO;
import model.entity.Avaliacao;

import java.util.ArrayList;
import java.util.List;

public class FakeAvaliacaoDAO implements AvaliacaoDAO {

    private static final List<Avaliacao> avaliacoes =
            new ArrayList<>();

    private static Long proximoId = 1L;

    @Override
    public void salvar(Avaliacao avaliacao) {

        avaliacao.setId(proximoId++);

        avaliacoes.add(avaliacao);
    }

    @Override
    public Avaliacao buscarPorId(Long id) {

        for (Avaliacao avaliacao : avaliacoes) {

            if (avaliacao.getId().equals(id)) {
                return avaliacao;
            }
        }

        return null;
    }

    @Override
    public Avaliacao buscarPorContratacao(Long contratacaoId) {

        for (Avaliacao avaliacao : avaliacoes) {

            if (avaliacao.getContratacaoId().equals(contratacaoId)) {
                return avaliacao;
            }
        }

        return null;
    }

    @Override
    public List<Avaliacao> listarPorPrestador(Long prestadorId) {

        List<Avaliacao> resultado =
                new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {

            if (avaliacao.getPrestadorId().equals(prestadorId)) {
                resultado.add(avaliacao);
            }
        }

        return resultado;
    }

    @Override
    public List<Avaliacao> listarPorCliente(Long clienteId) {

        List<Avaliacao> resultado =
                new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {

            if (avaliacao.getClienteId().equals(clienteId)) {
                resultado.add(avaliacao);
            }
        }

        return resultado;
    }
}