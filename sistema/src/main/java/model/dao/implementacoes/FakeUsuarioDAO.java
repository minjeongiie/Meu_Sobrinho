package model.dao.implementacoes;

import model.dao.interfaces.UsuarioDAO;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class FakeUsuarioDAO implements UsuarioDAO {

    private static final List<Usuario> usuarios =
            new ArrayList<>();

    private static Long proximoId = 1L;

    @Override
    public void salvar(Usuario usuario) {

        usuario.setId(proximoId++);

        usuarios.add(usuario);

        System.out.println("=== USUÁRIO SALVO ===");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNomeCompleto());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Pergunta: " + usuario.getPerguntaSeguranca());
        System.out.println("Resposta: " + usuario.getRespostaSeguranca());

        if (usuario instanceof Cliente cliente) {

            System.out.println("Tipo: Cliente");
            System.out.println("CPF: " + cliente.getCpf());

        } else if (usuario instanceof Prestador prestador) {

            System.out.println("Tipo: Prestador");
            System.out.println("CPF/CNPJ: " + prestador.getCpfCnpj());
            System.out.println("Celular: " + prestador.getCelular());
            System.out.println("Descrição: " + prestador.getDescricao());
            System.out.println("Perfil Público: " + prestador.isPerfilPublico());

            if (prestador.getCategoria() != null) {

                System.out.println(
                        "Categoria: "
                                + prestador.getCategoria().getNome()
                );
            }
        }

        System.out.println("=====================");
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

    @Override
    public Usuario buscarPorId(Long id) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }

        return null;
    }
    @Override
    public List<Prestador> listarPrestadores() {

        List<Prestador> prestadores =
                new ArrayList<>();

        for (Usuario usuario : usuarios) {

            if (usuario instanceof Prestador prestador
                    && prestador.isPerfilPublico()) {

                prestadores.add(prestador);
            }
        }

        return prestadores;
    }
}