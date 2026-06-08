package model.dao.implementacoes;

import model.dao.interfaces.ContratacaoDAO;
import model.entity.Contratacao;

import java.util.ArrayList;
import java.util.List;

public class FakeContratacaoDAO implements ContratacaoDAO {

    private static final List<Contratacao> contratacoes = new ArrayList<>();
    private static Long proximoId = 1L;

    @Override
    public void salvar(Contratacao contratacao) {
        contratacao.setId(proximoId++);
        contratacoes.add(contratacao);
        System.out.println(
                "Contratação #" + contratacao.getId()
                        + " criada para prestador "
                        + contratacao.getPrestadorId()
        );
    }

    @Override
    public void atualizar(Contratacao contratacao) {

        for (int i = 0; i < contratacoes.size(); i++) {

            if (contratacoes.get(i).getId().equals(contratacao.getId())) {
                contratacoes.set(i, contratacao);
                return;
            }
        }
    }

    @Override
    public Contratacao buscarPorId(Long id) {
        for (Contratacao contratacao : contratacoes) {
            if (contratacao.getId().equals(id)) {
                return contratacao;
            }
        }

        return null;
    }

    @Override
    public List<Contratacao> listarPorCliente(Long clienteId) {
        List<Contratacao> resultado = new ArrayList<>();

        for (Contratacao contratacao : contratacoes) {
            if (contratacao.getClienteId().equals(clienteId)) {
                resultado.add(contratacao);
            }
        }

        return resultado;
    }

    @Override
    public List<Contratacao> listarPorPrestador(Long prestadorId) {
        List<Contratacao> resultado = new ArrayList<>();

        for (Contratacao contratacao : contratacoes) {
            if (contratacao.getPrestadorId().equals(prestadorId)) {
                resultado.add(contratacao);
            }
        }

        return resultado;
    }
}