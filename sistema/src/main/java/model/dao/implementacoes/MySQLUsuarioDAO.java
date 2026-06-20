package model.dao.implementacoes;

import config.Conexao;
import model.dao.interfaces.UsuarioDAO;
import model.entity.Categoria;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsuarioDAO implements UsuarioDAO {

    @Override
    public void salvar(Usuario usuario) {

        String sqlUsuario = """
                INSERT INTO Usuario
                (nomeCompleto, email, senha, fotoPerfil)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    sqlUsuario,
                    Statement.RETURN_GENERATED_KEYS
            )) {

                stmt.setString(1, usuario.getNomeCompleto());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                stmt.setString(4, usuario.getFotoPerfil());

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }

            if (usuario instanceof Cliente cliente) {
                salvarCliente(conn, cliente);
            } else if (usuario instanceof Prestador prestador) {
                salvarPrestador(conn, prestador);
            }

            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário.", e);
        }
    }

    private void salvarCliente(Connection conn, Cliente cliente)
            throws SQLException {

        String sql = """
                INSERT INTO Cliente
                (id, cpf)
                VALUES (?, ?)
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cliente.getId());
            stmt.setString(2, cliente.getCpf());
            stmt.executeUpdate();
        }
    }

    private void salvarPrestador(Connection conn, Prestador prestador)
            throws SQLException {

        String sql = """
                INSERT INTO Prestador
                (id, descricao, valorMedio, perfilPublico, portfolio, cpfCnpj, celular, categoriaId)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, prestador.getId());
            stmt.setString(2, prestador.getDescricao());
            stmt.setDouble(3, prestador.getValorMedio());
            stmt.setBoolean(4, prestador.isPerfilPublico());
            stmt.setString(5, prestador.getPortfolio());
            stmt.setString(6, prestador.getCpfCnpj());
            stmt.setString(7, prestador.getCelular());
            stmt.setLong(8, prestador.getCategoria().getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Usuario usuario) {

        String sqlUsuario = """
                UPDATE Usuario
                SET nomeCompleto = ?,
                    email = ?,
                    senha = ?,
                    fotoPerfil = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario)) {
                stmt.setString(1, usuario.getNomeCompleto());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                stmt.setString(4, usuario.getFotoPerfil());
                stmt.setLong(5, usuario.getId());

                stmt.executeUpdate();
            }

            if (usuario instanceof Cliente cliente) {
                atualizarCliente(conn, cliente);
            } else if (usuario instanceof Prestador prestador) {
                atualizarPrestador(conn, prestador);
            }

            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário.", e);
        }
    }

    private void atualizarCliente(Connection conn, Cliente cliente)
            throws SQLException {

        String sql = """
                UPDATE Cliente
                SET cpf = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getCpf());
            stmt.setLong(2, cliente.getId());
            stmt.executeUpdate();
        }
    }

    private void atualizarPrestador(Connection conn, Prestador prestador)
            throws SQLException {

        String sql = """
                UPDATE Prestador
                SET descricao = ?,
                    valorMedio = ?,
                    perfilPublico = ?,
                    portfolio = ?,
                    cpfCnpj = ?,
                    celular = ?,
                    categoriaId = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prestador.getDescricao());
            stmt.setDouble(2, prestador.getValorMedio());
            stmt.setBoolean(3, prestador.isPerfilPublico());
            stmt.setString(4, prestador.getPortfolio());
            stmt.setString(5, prestador.getCpfCnpj());
            stmt.setString(6, prestador.getCelular());
            stmt.setLong(7, prestador.getCategoria().getId());
            stmt.setLong(8, prestador.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public Usuario buscarPorEmail(String email) {

        String sql = """
                SELECT u.*,
                       c.cpf,
                       p.descricao,
                       p.valorMedio,
                       p.perfilPublico,
                       p.portfolio,
                       p.cpfCnpj,
                       p.celular,
                       cat.id AS categoriaId,
                       cat.nome AS categoriaNome,
                       cat.descricao AS categoriaDescricao
                FROM Usuario u
                LEFT JOIN Cliente c ON c.id = u.id
                LEFT JOIN Prestador p ON p.id = u.id
                LEFT JOIN Categoria cat ON cat.id = p.categoriaId
                WHERE u.email = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarUsuario(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por email.", e);
        }
    }

    @Override
    public Usuario buscarPorId(Long id) {

        String sql = """
                SELECT u.*,
                       c.cpf,
                       p.descricao,
                       p.valorMedio,
                       p.perfilPublico,
                       p.portfolio,
                       p.cpfCnpj,
                       p.celular,
                       cat.id AS categoriaId,
                       cat.nome AS categoriaNome,
                       cat.descricao AS categoriaDescricao
                FROM Usuario u
                LEFT JOIN Cliente c ON c.id = u.id
                LEFT JOIN Prestador p ON p.id = u.id
                LEFT JOIN Categoria cat ON cat.id = p.categoriaId
                WHERE u.id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarUsuario(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID.", e);
        }
    }

    @Override
    public List<Prestador> listarPrestadores() {

        String sql = """
                SELECT u.*,
                       p.descricao,
                       p.valorMedio,
                       p.perfilPublico,
                       p.portfolio,
                       p.cpfCnpj,
                       p.celular,
                       cat.id AS categoriaId,
                       cat.nome AS categoriaNome,
                       cat.descricao AS categoriaDescricao
                FROM Usuario u
                INNER JOIN Prestador p ON p.id = u.id
                INNER JOIN Categoria cat ON cat.id = p.categoriaId
                WHERE p.perfilPublico = TRUE
                """;

        List<Prestador> prestadores = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prestadores.add(montarPrestador(rs));
            }

            return prestadores;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar prestadores.", e);
        }
    }

    @Override
    public List<Prestador> buscarPrestadores(String termo) {

        if (termo == null || termo.trim().isEmpty()) {
            return listarPrestadores();
        }

        String sql = """
                SELECT u.*,
                       p.descricao,
                       p.valorMedio,
                       p.perfilPublico,
                       p.portfolio,
                       p.cpfCnpj,
                       p.celular,
                       cat.id AS categoriaId,
                       cat.nome AS categoriaNome,
                       cat.descricao AS categoriaDescricao
                FROM Usuario u
                INNER JOIN Prestador p ON p.id = u.id
                INNER JOIN Categoria cat ON cat.id = p.categoriaId
                WHERE p.perfilPublico = TRUE
                AND (
                    LOWER(u.nomeCompleto) LIKE ?
                    OR LOWER(p.descricao) LIKE ?
                    OR LOWER(cat.nome) LIKE ?
                )
                """;

        List<Prestador> prestadores = new ArrayList<>();

        String termoBusca = "%" + termo.toLowerCase() + "%";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, termoBusca);
            stmt.setString(2, termoBusca);
            stmt.setString(3, termoBusca);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prestadores.add(montarPrestador(rs));
            }

            return prestadores;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prestadores.", e);
        }
    }

    private Usuario montarUsuario(ResultSet rs)
            throws SQLException {

        if (rs.getString("cpf") != null) {
            Cliente cliente = new Cliente();

            preencherUsuario(cliente, rs);
            cliente.setCpf(rs.getString("cpf"));

            return cliente;
        }

        if (rs.getString("cpfCnpj") != null) {
            return montarPrestador(rs);
        }

        return null;
    }

    private Prestador montarPrestador(ResultSet rs)
            throws SQLException {

        Prestador prestador = new Prestador();

        preencherUsuario(prestador, rs);

        prestador.setDescricao(rs.getString("descricao"));
        prestador.setValorMedio(rs.getDouble("valorMedio"));
        prestador.setPerfilPublico(rs.getBoolean("perfilPublico"));
        prestador.setPortfolio(rs.getString("portfolio"));
        prestador.setCpfCnpj(rs.getString("cpfCnpj"));
        prestador.setCelular(rs.getString("celular"));

        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoriaId"));
        categoria.setNome(rs.getString("categoriaNome"));

        prestador.setCategoria(categoria);

        return prestador;
    }

    private void preencherUsuario(
            Usuario usuario,
            ResultSet rs
    ) throws SQLException {

        usuario.setId(rs.getLong("id"));
        usuario.setNomeCompleto(rs.getString("nomeCompleto"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setFotoPerfil(rs.getString("fotoPerfil"));
    }
}