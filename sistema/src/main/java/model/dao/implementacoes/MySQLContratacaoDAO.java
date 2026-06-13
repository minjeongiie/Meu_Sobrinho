package model.dao.implementacoes;

import config.Conexao;
import model.dao.interfaces.ContratacaoDAO;
import model.entity.Contratacao;
import model.entity.StatusContratacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLContratacaoDAO implements ContratacaoDAO {

    @Override
    public void salvar(Contratacao contratacao) {

        String sql = """
                INSERT INTO Contratacao
                (clienteId, prestadorId, descricao, preco, dataSolicitada, status, valorContraproposta, mensagemContraproposta)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            stmt.setLong(1, contratacao.getClienteId());
            stmt.setLong(2, contratacao.getPrestadorId());
            stmt.setString(3, contratacao.getDescricao());

            if (contratacao.getPreco() != null) {
                stmt.setDouble(4, contratacao.getPreco());
            } else {
                stmt.setNull(4, Types.DECIMAL);
            }

            if (contratacao.getDataSolicitada() != null) {
                stmt.setDate(
                        5,
                        Date.valueOf(contratacao.getDataSolicitada())
                );
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setString(6, contratacao.getStatus().name());

            if (contratacao.getValorContraproposta() != null) {
                stmt.setDouble(7, contratacao.getValorContraproposta());
            } else {
                stmt.setNull(7, Types.DECIMAL);
            }

            stmt.setString(8, contratacao.getMensagemContraproposta());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                contratacao.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao salvar contratação.",
                    e
            );
        }
    }

    @Override
    public void atualizar(Contratacao contratacao) {

        String sql = """
                UPDATE Contratacao
                SET clienteId = ?,
                    prestadorId = ?,
                    descricao = ?,
                    preco = ?,
                    dataSolicitada = ?,
                    status = ?,
                    valorContraproposta = ?,
                    mensagemContraproposta = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, contratacao.getClienteId());
            stmt.setLong(2, contratacao.getPrestadorId());
            stmt.setString(3, contratacao.getDescricao());

            if (contratacao.getPreco() != null) {
                stmt.setDouble(4, contratacao.getPreco());
            } else {
                stmt.setNull(4, Types.DECIMAL);
            }

            if (contratacao.getDataSolicitada() != null) {
                stmt.setDate(
                        5,
                        Date.valueOf(contratacao.getDataSolicitada())
                );
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setString(6, contratacao.getStatus().name());

            if (contratacao.getValorContraproposta() != null) {
                stmt.setDouble(7, contratacao.getValorContraproposta());
            } else {
                stmt.setNull(7, Types.DECIMAL);
            }

            stmt.setString(8, contratacao.getMensagemContraproposta());
            stmt.setLong(9, contratacao.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao atualizar contratação.",
                    e
            );
        }
    }

    @Override
    public Contratacao buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM Contratacao
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarContratacao(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao buscar contratação por ID.",
                    e
            );
        }
    }

    @Override
    public List<Contratacao> listarPorCliente(Long clienteId) {

        String sql = """
                SELECT *
                FROM Contratacao
                WHERE clienteId = ?
                ORDER BY id DESC
                """;

        List<Contratacao> contratacoes =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, clienteId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                contratacoes.add(montarContratacao(rs));
            }

            return contratacoes;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao listar contratações por cliente.",
                    e
            );
        }
    }

    @Override
    public List<Contratacao> listarPorPrestador(Long prestadorId) {

        String sql = """
                SELECT *
                FROM Contratacao
                WHERE prestadorId = ?
                ORDER BY id DESC
                """;

        List<Contratacao> contratacoes =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, prestadorId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                contratacoes.add(montarContratacao(rs));
            }

            return contratacoes;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao listar contratações por prestador.",
                    e
            );
        }
    }

    private Contratacao montarContratacao(ResultSet rs)
            throws SQLException {

        Contratacao contratacao =
                new Contratacao();

        contratacao.setId(rs.getLong("id"));
        contratacao.setClienteId(rs.getLong("clienteId"));
        contratacao.setPrestadorId(rs.getLong("prestadorId"));
        contratacao.setDescricao(rs.getString("descricao"));

        double preco =
                rs.getDouble("preco");

        if (!rs.wasNull()) {
            contratacao.setPreco(preco);
        }

        Date dataSolicitada =
                rs.getDate("dataSolicitada");

        if (dataSolicitada != null) {
            contratacao.setDataSolicitada(
                    dataSolicitada.toLocalDate()
            );
        }

        contratacao.setStatus(
                StatusContratacao.valueOf(
                        rs.getString("status")
                )
        );

        double valorContraproposta =
                rs.getDouble("valorContraproposta");

        if (!rs.wasNull()) {
            contratacao.setValorContraproposta(
                    valorContraproposta
            );
        }

        contratacao.setMensagemContraproposta(
                rs.getString("mensagemContraproposta")
        );

        return contratacao;
    }
}