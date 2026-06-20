package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Categoria;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.CategoriaServiceImp;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.CategoriaService;
import model.service.interfaces.UsuarioService;

import java.io.IOException;

@WebServlet("/editar-perfil")
public class EditarPerfilController extends HttpServlet {

    private UsuarioService usuarioService;
    private CategoriaService categoriaService;

    @Override
    public void init() {

        usuarioService = new UsuarioServiceImp();
        categoriaService = new CategoriaServiceImp();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect(request.getContextPath() + "/views/geral/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario instanceof Cliente) {
            request.getRequestDispatcher("/views/cliente/edit-client-profile.jsp")
                    .forward(request, response);

        } else if (usuario instanceof Prestador) {
            request.getRequestDispatcher("/views/prestador/edit-provider-profile.jsp")
                    .forward(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/views/geral/home.jsp");
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect(request.getContextPath() + "/views/geral/login.jsp");
            return;
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        String senhaAtual = request.getParameter("senhaAtual");

        try {

            Usuario usuarioAtualizado;

            if (usuarioLogado instanceof Cliente clienteLogado) {

                Cliente cliente = new Cliente();

                cliente.setId(clienteLogado.getId());
                cliente.setNomeCompleto(request.getParameter("name"));
                cliente.setEmail(clienteLogado.getEmail());
                cliente.setSenha(clienteLogado.getSenha());
                cliente.setFotoPerfil(clienteLogado.getFotoPerfil());

                cliente.setCpf(request.getParameter("cpf"));

                usuarioAtualizado = cliente;

            } else if (usuarioLogado instanceof Prestador prestadorLogado) {

                Prestador prestador = new Prestador();

                prestador.setId(prestadorLogado.getId());
                prestador.setNomeCompleto(request.getParameter("name"));
                prestador.setEmail(prestadorLogado.getEmail());
                prestador.setSenha(prestadorLogado.getSenha());
                prestador.setFotoPerfil(prestadorLogado.getFotoPerfil());

                prestador.setCpfCnpj(request.getParameter("doc"));
                prestador.setCelular(request.getParameter("celular"));
                prestador.setDescricao(request.getParameter("bio"));
                prestador.setPortfolio(prestadorLogado.getPortfolio());

                String categoriaIdParam = request.getParameter("categoriaId");

                if (categoriaIdParam != null && !categoriaIdParam.trim().isEmpty()) {

                    Long categoriaId = Long.parseLong(categoriaIdParam);

                    Categoria categoria =
                            categoriaService.buscarPorId(categoriaId);

                    prestador.setCategoria(categoria);

                } else {
                    prestador.setCategoria(prestadorLogado.getCategoria());
                }

                String valorMedio = request.getParameter("valorMedio");

                if (valorMedio == null || valorMedio.trim().isEmpty()) {
                    prestador.setValorMedio(0.0);
                } else {
                    prestador.setValorMedio(Double.parseDouble(valorMedio));
                }

                prestador.setPerfilPublico(
                        Boolean.parseBoolean(request.getParameter("perfilPublico"))
                );

                usuarioAtualizado = prestador;

            } else {
                response.sendRedirect(request.getContextPath() + "/views/geral/home.jsp");
                return;
            }

            usuarioService.atualizarPerfil(usuarioAtualizado, senhaAtual);

            session.setAttribute("usuarioLogado", usuarioAtualizado);

            response.sendRedirect(request.getContextPath() + "/perfil");

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            if (usuarioLogado instanceof Cliente) {
                request.getRequestDispatcher("/views/cliente/edit-client-profile.jsp")
                        .forward(request, response);

            } else {
                request.getRequestDispatcher("/views/prestador/edit-provider-profile.jsp")
                        .forward(request, response);
            }
        }
    }
}