package model.dao.implementacoes;

import config.Conexao;
import model.dao.interfaces.SolicitacaoServicoDAO;
import model.entity.Categoria;
import model.entity.SolicitacaoServico;
import model.entity.StatusSolicitacaoServico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLSolicitacaoServicoDAO implements SolicitacaoServicoDAO {

    @Override
    public void salvar(SolicitacaoServico solicitacao) {

        String sql = """
                INSERT INTO SolicitacaoServico
                (clienteId, titulo, descricao, valorEstimado, categoriaId, dataDesejada, status)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            stmt.setLong(1, solicitacao.getClienteId());
            stmt.setString(2, solicitacao.getTitulo());
            stmt.setString(3, solicitacao.getDescricao());

            if (solicitacao.getValorEstimado() == null) {
                stmt.setNull(4, Types.DECIMAL);
            } else {
                stmt.setDouble(4, solicitacao.getValorEstimado());
            }

            stmt.setLong(5, solicitacao.getCategoria().getId());
            stmt.setDate(6, Date.valueOf(solicitacao.getDataDesejada()));
            stmt.setString(7, solicitacao.getStatus().name());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                solicitacao.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar solicitação.", e);
        }
    }

    @Override
    public void atualizar(SolicitacaoServico solicitacao) {

        String sql = """
                UPDATE SolicitacaoServico
                SET clienteId = ?,
                    titulo = ?,
                    descricao = ?,
                    valorEstimado = ?,
                    categoriaId = ?,
                    dataDesejada = ?,
                    status = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, solicitacao.getClienteId());
            stmt.setString(2, solicitacao.getTitulo());
            stmt.setString(3, solicitacao.getDescricao());

            if (solicitacao.getValorEstimado() == null) {
                stmt.setNull(4, Types.DECIMAL);
            } else {
                stmt.setDouble(4, solicitacao.getValorEstimado());
            }

            stmt.setLong(5, solicitacao.getCategoria().getId());
            stmt.setDate(6, Date.valueOf(solicitacao.getDataDesejada()));
            stmt.setString(7, solicitacao.getStatus().name());
            stmt.setLong(8, solicitacao.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar solicitação.", e);
        }
    }

    @Override
    public SolicitacaoServico buscarPorId(Long id) {

        String sql = """
                SELECT s.*,
                       c.nome AS categoriaNome,
                       c.descricao AS categoriaDescricao
                FROM SolicitacaoServico s
                INNER JOIN Categoria c ON c.id = s.categoriaId
                WHERE s.id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarSolicitacao(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar solicitação.", e);
        }
    }

    @Override
    public List<SolicitacaoServico> listarTodas() {

        String sql = """
                SELECT s.*,
                       c.nome AS categoriaNome,
                       c.descricao AS categoriaDescricao
                FROM SolicitacaoServico s
                INNER JOIN Categoria c ON c.id = s.categoriaId
                ORDER BY s.id DESC
                """;

        return listarPorSql(sql);
    }

    @Override
    public List<SolicitacaoServico> listarAbertas() {

        String sql = """
                SELECT s.*,
                       c.nome AS categoriaNome,
                       c.descricao AS categoriaDescricao
                FROM SolicitacaoServico s
                INNER JOIN Categoria c ON c.id = s.categoriaId
                WHERE s.status = 'ABERTA'
                ORDER BY s.id DESC
                """;

        return listarPorSql(sql);
    }

    @Override
    public List<SolicitacaoServico> listarPorCliente(Long clienteId) {

        String sql = """
                SELECT s.*,
                       c.nome AS categoriaNome,
                       c.descricao AS categoriaDescricao
                FROM SolicitacaoServico s
                INNER JOIN Categoria c ON c.id = s.categoriaId
                WHERE s.clienteId = ?
                ORDER BY s.id DESC
                """;

        List<SolicitacaoServico> solicitacoes =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, clienteId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                solicitacoes.add(montarSolicitacao(rs));
            }

            return solicitacoes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar solicitações do cliente.", e);
        }
    }

    private List<SolicitacaoServico> listarPorSql(String sql) {

        List<SolicitacaoServico> solicitacoes =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                solicitacoes.add(montarSolicitacao(rs));
            }

            return solicitacoes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar solicitações.", e);
        }
    }

    private SolicitacaoServico montarSolicitacao(ResultSet rs)
            throws SQLException {

        SolicitacaoServico solicitacao =
                new SolicitacaoServico();

        solicitacao.setId(rs.getLong("id"));
        solicitacao.setClienteId(rs.getLong("clienteId"));
        solicitacao.setTitulo(rs.getString("titulo"));
        solicitacao.setDescricao(rs.getString("descricao"));

        double valorEstimado =
                rs.getDouble("valorEstimado");

        if (rs.wasNull()) {
            solicitacao.setValorEstimado(null);
        } else {
            solicitacao.setValorEstimado(valorEstimado);
        }

        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoriaId"));
        categoria.setNome(rs.getString("categoriaNome"));
        categoria.setDescricao(rs.getString("categoriaDescricao"));

        solicitacao.setCategoria(categoria);

        solicitacao.setDataDesejada(
                rs.getDate("dataDesejada").toLocalDate()
        );

        solicitacao.setStatus(
                StatusSolicitacaoServico.valueOf(
                        rs.getString("status")
                )
        );

        return solicitacao;
    }
}