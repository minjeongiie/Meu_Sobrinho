package model.dao.implementacoes;

import config.Conexao;
import model.dao.interfaces.PropostaServicoDAO;
import model.entity.PropostaServico;
import model.entity.StatusPropostaServico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPropostaServicoDAO implements PropostaServicoDAO {

    @Override
    public void salvar(PropostaServico proposta) {

        String sql = """
                INSERT INTO PropostaServico
                (solicitacaoId, prestadorId, valorProposto, descricao, prazoConclusao, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            stmt.setLong(1, proposta.getSolicitacaoId());
            stmt.setLong(2, proposta.getPrestadorId());
            stmt.setDouble(3, proposta.getValorProposto());
            stmt.setString(4, proposta.getDescricao());
            stmt.setDate(5, Date.valueOf(proposta.getPrazoConclusao()));
            stmt.setString(6, proposta.getStatus().name());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                proposta.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar proposta.", e);
        }
    }

    @Override
    public void atualizar(PropostaServico proposta) {

        String sql = """
                UPDATE PropostaServico
                SET solicitacaoId = ?,
                    prestadorId = ?,
                    valorProposto = ?,
                    descricao = ?,
                    prazoConclusao = ?,
                    status = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, proposta.getSolicitacaoId());
            stmt.setLong(2, proposta.getPrestadorId());
            stmt.setDouble(3, proposta.getValorProposto());
            stmt.setString(4, proposta.getDescricao());
            stmt.setDate(5, Date.valueOf(proposta.getPrazoConclusao()));
            stmt.setString(6, proposta.getStatus().name());
            stmt.setLong(7, proposta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar proposta.", e);
        }
    }

    @Override
    public PropostaServico buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM PropostaServico
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarProposta(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar proposta.", e);
        }
    }

    @Override
    public List<PropostaServico> listarPorSolicitacao(Long solicitacaoId) {

        String sql = """
                SELECT *
                FROM PropostaServico
                WHERE solicitacaoId = ?
                ORDER BY id DESC
                """;

        List<PropostaServico> propostas =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, solicitacaoId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                propostas.add(montarProposta(rs));
            }

            return propostas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar propostas da solicitação.", e);
        }
    }

    @Override
    public List<PropostaServico> listarPorPrestador(Long prestadorId) {

        String sql = """
                SELECT *
                FROM PropostaServico
                WHERE prestadorId = ?
                ORDER BY id DESC
                """;

        List<PropostaServico> propostas =
                new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, prestadorId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                propostas.add(montarProposta(rs));
            }

            return propostas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar propostas do prestador.", e);
        }
    }

    private PropostaServico montarProposta(ResultSet rs)
            throws SQLException {

        PropostaServico proposta =
                new PropostaServico();

        proposta.setId(rs.getLong("id"));
        proposta.setSolicitacaoId(rs.getLong("solicitacaoId"));
        proposta.setPrestadorId(rs.getLong("prestadorId"));
        proposta.setValorProposto(rs.getDouble("valorProposto"));
        proposta.setDescricao(rs.getString("descricao"));
        proposta.setPrazoConclusao(
                rs.getDate("prazoConclusao").toLocalDate()
        );

        proposta.setStatus(
                StatusPropostaServico.valueOf(
                        rs.getString("status")
                )
        );

        return proposta;
    }
}