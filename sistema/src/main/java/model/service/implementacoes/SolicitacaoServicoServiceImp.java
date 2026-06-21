package model.service.implementacoes;

import model.dao.implementacoes.FakeSolicitacaoServicoDAO;
import model.dao.interfaces.SolicitacaoServicoDAO;
import model.entity.Categoria;
import model.entity.SolicitacaoServico;
import model.entity.StatusSolicitacaoServico;
import model.service.interfaces.SolicitacaoServicoService;

import java.time.LocalDate;
import java.util.List;

public class SolicitacaoServicoServiceImp
        implements SolicitacaoServicoService {

    private final SolicitacaoServicoDAO solicitacaoDAO;

    public SolicitacaoServicoServiceImp() {
        this.solicitacaoDAO = new FakeSolicitacaoServicoDAO();
    }

    @Override
    public void publicarSolicitacao(
            Long clienteId,
            String titulo,
            String descricao,
            Double valorEstimado,
            Categoria categoria,
            LocalDate dataDesejada
    ) {

        if (clienteId == null) {
            throw new IllegalArgumentException(
                    "Cliente obrigatório."
            );
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Título obrigatório."
            );
        }

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Descrição obrigatória."
            );
        }

        if (categoria == null || categoria.getId() == null) {
            throw new IllegalArgumentException(
                    "Categoria obrigatória."
            );
        }

        if (dataDesejada == null) {
            throw new IllegalArgumentException(
                    "Data desejada obrigatória."
            );
        }

        SolicitacaoServico solicitacao =
                new SolicitacaoServico();

        solicitacao.setClienteId(clienteId);
        solicitacao.setTitulo(titulo);
        solicitacao.setDescricao(descricao);
        solicitacao.setValorEstimado(valorEstimado);
        solicitacao.setCategoria(categoria);
        solicitacao.setDataDesejada(dataDesejada);
        solicitacao.setStatus(StatusSolicitacaoServico.ABERTA);

        solicitacaoDAO.salvar(solicitacao);
    }

    @Override
    public void fecharSolicitacao(Long solicitacaoId) {

        SolicitacaoServico solicitacao =
                solicitacaoDAO.buscarPorId(solicitacaoId);

        if (solicitacao == null) {
            throw new IllegalArgumentException(
                    "Solicitação não encontrada."
            );
        }

        solicitacao.setStatus(
                StatusSolicitacaoServico.FECHADA
        );

        solicitacaoDAO.atualizar(solicitacao);
    }

    @Override
    public SolicitacaoServico buscarPorId(Long id) {

        return solicitacaoDAO.buscarPorId(id);
    }

    @Override
    public List<SolicitacaoServico> listarTodas() {

        return solicitacaoDAO.listarTodas();
    }

    @Override
    public List<SolicitacaoServico> listarAbertas() {

        return solicitacaoDAO.listarAbertas();
    }

    @Override
    public List<SolicitacaoServico> listarPorCliente(Long clienteId) {

        return solicitacaoDAO.listarPorCliente(clienteId);
    }
}