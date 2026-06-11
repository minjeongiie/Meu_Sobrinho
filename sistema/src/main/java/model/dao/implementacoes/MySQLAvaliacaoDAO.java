package model.dao.implementacoes;

import config.Conexao;
import model.dao.interfaces.AvaliacaoDAO;
import model.entity.Avaliacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAvaliacaoDAO implements AvaliacaoDAO {

    @Override
    public void salvar(Avaliacao avaliacao) {

        String sql = """
                INSERT INTO Avaliacao
                (contratacaoId, clienteId, prestadorId, nota, comentario, dataAvaliacao)
                VALUES (?, ?, ?, ?, ?, CURRENT_DATE)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            stmt.setLong(1, avaliacao.getContratacaoId());
            stmt.setLong(2, avaliacao.getClienteId());
            stmt.setLong(3, avaliacao.getPrestadorId());
            stmt.setInt(4, avaliacao.getNota());
            stmt.setString(5, avaliacao.getComentario());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                avaliacao.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao salvar avaliação.",
                    e
            );
        }
    }

    @Override
    public Avaliacao buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM Avaliacao
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarAvaliacao(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao buscar avaliação por ID.",
                    e
            );
        }
    }

    @Override
    public Avaliacao buscarPorContratacao(Long contratacaoId) {

        String sql = """
                SELECT *
                FROM Avaliacao
                WHERE contratacaoId = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, contratacaoId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarAvaliacao(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao buscar avaliação por contratação.",
                    e
            );
        }
    }

    @Override
    public List<Avaliacao> listarPorPrestador(Long prestadorId) {

        String sql = """
                SELECT *
                FROM Avaliacao
                WHERE prestadorId = ?
                ORDER BY id DESC
                """;

        List<Avaliacao> avaliacoes =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, prestadorId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                avaliacoes.add(montarAvaliacao(rs));
            }

            return avaliacoes;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao listar avaliações por prestador.",
                    e
            );
        }
    }

    @Override
    public List<Avaliacao> listarPorCliente(Long clienteId) {

        String sql = """
                SELECT *
                FROM Avaliacao
                WHERE clienteId = ?
                ORDER BY id DESC
                """;

        List<Avaliacao> avaliacoes =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, clienteId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                avaliacoes.add(montarAvaliacao(rs));
            }

            return avaliacoes;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao listar avaliações por cliente.",
                    e
            );
        }
    }

    private Avaliacao montarAvaliacao(ResultSet rs)
            throws SQLException {

        Avaliacao avaliacao =
                new Avaliacao();

        avaliacao.setId(rs.getLong("id"));
        avaliacao.setContratacaoId(rs.getLong("contratacaoId"));
        avaliacao.setClienteId(rs.getLong("clienteId"));
        avaliacao.setPrestadorId(rs.getLong("prestadorId"));
        avaliacao.setNota(rs.getInt("nota"));
        avaliacao.setComentario(rs.getString("comentario"));

        return avaliacao;
    }
}