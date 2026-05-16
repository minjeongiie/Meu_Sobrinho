package model.dao.implementacoes;

import model.dao.interfaces.UsuarioDAO;
import model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class FakeUsuarioDAO implements UsuarioDAO {

    private static final List<Usuario> usuarios =
            new ArrayList<>();

    @Override
    public void salvar(Usuario usuario) {

        usuarios.add(usuario);

        System.out.println("Usuário salvo com sucesso:");
        System.out.println(usuario.getNomeCompleto());
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getRespostaSeguranca());
        System.out.println(usuario.getSenha());
        System.out.println(usuario.getPerguntaSeguranca());
    }

    @Override
    public Usuario buscarPorEmail(String email) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }

        return null;
    }
}
