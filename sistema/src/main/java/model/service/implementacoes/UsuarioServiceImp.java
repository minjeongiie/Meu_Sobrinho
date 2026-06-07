package model.service.implementacoes;

import model.dao.implementacoes.FakeUsuarioDAO;
import model.dao.interfaces.UsuarioDAO;
import model.entity.Prestador;
import util.UsuarioJaExisteException;
import model.entity.Usuario;
import model.service.interfaces.UsuarioService;
import util.Criptografia;

import java.util.List;

public class UsuarioServiceImp
        implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImp() {
        this.usuarioDAO = new FakeUsuarioDAO();
    }

    @Override
    public void cadastrar(
            Usuario usuario,
            String confirmarSenha
    ) {

        validarCampos(usuario);

        validarConfirmacaoSenha(
                usuario.getSenha(),
                confirmarSenha
        );

        verificarEmailDuplicado(
                usuario.getEmail()
        );

        String senhaCriptografada =
                Criptografia.criptografarSenha(
                        usuario.getSenha()
                );

        usuario.setSenha(senhaCriptografada);

        usuarioDAO.salvar(usuario);
    }

    private void validarCampos(Usuario usuario) {

        if (usuario.getNomeCompleto() == null
                || usuario.getNomeCompleto()
                .trim()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Nome obrigatório"
            );
        }

        if (usuario.getEmail() == null
                || usuario.getEmail()
                .trim()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Email obrigatório"
            );
        }

        if (usuario.getSenha() == null
                || usuario.getSenha()
                .trim()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Senha obrigatória"
            );
        }
    }

    @Override
    public Usuario autenticar(String email, String senha
    ) {

        Usuario usuario =
                usuarioDAO.buscarPorEmail(email);

        if (usuario == null) {

            throw new IllegalArgumentException(
                    "Email ou senha inválidos"
            );
        }

        boolean senhaCorreta =
                Criptografia.verificarSenha(
                        senha,
                        usuario.getSenha()
                );

        if (!senhaCorreta) {

            throw new IllegalArgumentException(
                    "Email ou senha inválidos"
            );
        }

        return usuario;
    }

    @Override
    public void atualizarPerfil(
            Usuario usuario,
            String senhaAtual
    ) {

        Usuario usuarioBanco =
                usuarioDAO.buscarPorId(usuario.getId());

        if (usuarioBanco == null) {

            throw new IllegalArgumentException(
                    "Usuário não encontrado."
            );
        }

        boolean senhaCorreta =
                Criptografia.verificarSenha(
                        senhaAtual,
                        usuarioBanco.getSenha()
                );

        if (!senhaCorreta) {

            throw new IllegalArgumentException(
                    "Senha atual incorreta."
            );
        }

        usuarioDAO.atualizar(usuario);
    }

    private void validarConfirmacaoSenha(
            String senha,
            String confirmarSenha
    ) {

        if (!senha.equals(confirmarSenha)) {

            throw new IllegalArgumentException(
                    "As senhas não coincidem"
            );
        }
    }

    private void verificarEmailDuplicado(
            String email
    ) {

        Usuario usuarioExistente =
                usuarioDAO.buscarPorEmail(email);

        if (usuarioExistente != null) {

            throw new UsuarioJaExisteException(
                    "Já existe um usuário cadastrado com esse email"
            );
        }
    }

    @Override
    public Usuario buscarPorId(Long id) {

        return usuarioDAO.buscarPorId(id);
    }

    @Override
    public List<Prestador> listarPrestadores() {

        return usuarioDAO.listarPrestadores();
    }

    @Override
    public List<Prestador> buscarPrestadores(String termo) {

        return usuarioDAO.buscarPrestadores(termo);
    }
}