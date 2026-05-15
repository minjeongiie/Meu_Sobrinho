package model.service.implementacoes;

import model.dao.implementacoes.FakeUsuarioDAO;
import model.dao.interfaces.UsuarioDAO;
import util.UsuarioJaExisteException;
import model.entity.Usuario;
import model.service.interfaces.UsuarioService;
import util.Criptografia;

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
}