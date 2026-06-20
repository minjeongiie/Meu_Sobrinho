package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Categoria;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.CategoriaServiceImp;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.CategoriaService;
import model.service.interfaces.UsuarioService;
import util.UsuarioJaExisteException;

import java.io.IOException;

@WebServlet("/cadastro")
public class UsuarioController extends HttpServlet {

    private UsuarioService usuarioService;
    private CategoriaService categoriaService;

    @Override
    public void init() {
        usuarioService = new UsuarioServiceImp();
        categoriaService = new CategoriaServiceImp();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String tipoUsuario = request.getParameter("tipoUsuario");

        try {
            String senha = request.getParameter("password");
            String confirmarSenha = request.getParameter("password2");

            Usuario usuario;

            if ("CLIENTE".equals(tipoUsuario)) {

                Cliente cliente = new Cliente();
                cliente.setCpf(request.getParameter("cpf"));

                usuario = cliente;

            } else if ("PRESTADOR".equals(tipoUsuario)) {

                Prestador prestador = new Prestador();

                prestador.setCpfCnpj(request.getParameter("doc"));
                prestador.setCelular(request.getParameter("celular"));
                prestador.setDescricao(request.getParameter("bio"));
                prestador.setPerfilPublico(true);
                prestador.setValorMedio(0.0);
                prestador.setPortfolio(null);

                String categoriaParam =
                        request.getParameter("category");

                if (categoriaParam == null
                        || categoriaParam.trim().isEmpty()) {

                    throw new IllegalArgumentException(
                            "Categoria obrigatória."
                    );
                }

                Long categoriaId =
                        Long.parseLong(categoriaParam);

                Categoria categoria =
                        categoriaService.buscarPorId(categoriaId);

                if (categoria == null) {
                    throw new IllegalArgumentException(
                            "Categoria inválida."
                    );
                }

                prestador.setCategoria(categoria);

                usuario = prestador;

            } else {
                throw new IllegalArgumentException(
                        "Tipo de usuário inválido."
                );
            }

            usuario.setNomeCompleto(request.getParameter("name"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(senha);

            usuarioService.cadastrar(usuario, confirmarSenha);

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/geral/login.jsp"
                            + "?registered=true"
            );

        } catch (IllegalArgumentException | UsuarioJaExisteException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            if ("CLIENTE".equals(tipoUsuario)) {

                request.getRequestDispatcher(
                        "/views/geral/register.jsp"
                ).forward(request, response);

            } else {

                request.getRequestDispatcher(
                        "/views/prestador/register-provider.jsp"
                ).forward(request, response);
            }
        }
    }
}