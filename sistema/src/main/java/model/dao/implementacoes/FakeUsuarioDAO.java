package model.dao.implementacoes;

import model.dao.interfaces.UsuarioDAO;
import model.entity.Categoria;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;
import util.Criptografia;

import java.util.ArrayList;
import java.util.List;

public class FakeUsuarioDAO implements UsuarioDAO {

    private static final List<Usuario> usuarios =
            new ArrayList<>();

    private static Long proximoId = 1L;

    static {
        Cliente cliente = new Cliente();
        cliente.setId(proximoId++);
        cliente.setNomeCompleto("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setSenha(Criptografia.criptografarSenha("123"));
        cliente.setCpf("12345678900");
        cliente.setPerguntaSeguranca("Nome do primeiro pet?");
        cliente.setRespostaSeguranca("Rex");

        usuarios.add(cliente);

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Desenvolvimento Web");

        Prestador prestador = new Prestador();
        prestador.setId(proximoId++);
        prestador.setNomeCompleto("Prestador Teste");
        prestador.setEmail("prestador@teste.com");
        prestador.setSenha(Criptografia.criptografarSenha("123"));
        prestador.setCpfCnpj("12345678901");
        prestador.setCelular("(21) 99999-9999");
        prestador.setCategoria(categoria);
        prestador.setDescricao("Criação de sites, sistemas web e manutenção de aplicações.");
        prestador.setValorMedio(150.00);
        prestador.setPerfilPublico(true);
        prestador.setPerguntaSeguranca("Nome do primeiro pet?");
        prestador.setRespostaSeguranca("Rex");

        usuarios.add(prestador);
    }

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
    public void atualizar(Usuario usuarioAtualizado) {

        for (int i = 0; i < usuarios.size(); i++) {

            Usuario usuario = usuarios.get(i);

            if (usuario.getId().equals(usuarioAtualizado.getId())) {

                usuarios.set(i, usuarioAtualizado);

                System.out.println("=== USUÁRIO ATUALIZADO ===");
                System.out.println("ID: " + usuarioAtualizado.getId());
                System.out.println("Nome: " + usuarioAtualizado.getNomeCompleto());
                System.out.println("Email: " + usuarioAtualizado.getEmail());
                System.out.println("===========================");

                return;
            }
        }

        throw new IllegalArgumentException(
                "Usuário não encontrado para atualização."
        );
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
    @Override
    public List<Prestador> buscarPrestadores(String termo) {

        List<Prestador> resultados =
                new ArrayList<>();

        if (termo == null || termo.trim().isEmpty()) {

            return listarPrestadores();
        }

        String termoBusca =
                termo.toLowerCase();

        for (Usuario usuario : usuarios) {

            if (!(usuario instanceof Prestador prestador)) {
                continue;
            }

            if (!prestador.isPerfilPublico()) {
                continue;
            }

            boolean encontrouNoNome =
                    prestador.getNomeCompleto() != null
                            && prestador.getNomeCompleto()
                            .toLowerCase()
                            .contains(termoBusca);

            boolean encontrouNaDescricao =
                    prestador.getDescricao() != null
                            && prestador.getDescricao()
                            .toLowerCase()
                            .contains(termoBusca);

            boolean encontrouNaCategoria =
                    prestador.getCategoria() != null
                            && prestador.getCategoria().getNome() != null
                            && prestador.getCategoria()
                            .getNome()
                            .toLowerCase()
                            .contains(termoBusca);

            if (encontrouNoNome
                    || encontrouNaDescricao
                    || encontrouNaCategoria) {

                resultados.add(prestador);
            }
        }

        return resultados;
    }
}