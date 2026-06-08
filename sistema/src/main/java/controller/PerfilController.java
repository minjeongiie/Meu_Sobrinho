package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Cliente;
import model.entity.Contratacao;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.ContratacaoServiceImp;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.ContratacaoService;
import model.service.interfaces.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/perfil")
public class PerfilController extends HttpServlet {

    private ContratacaoService contratacaoService;
    private UsuarioService usuarioService;

    @Override
    public void init() {
        contratacaoService = new ContratacaoServiceImp();
        usuarioService = new UsuarioServiceImp();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("usuarioLogado") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/geral/login.jsp"
            );

            return;
        }

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario instanceof Cliente) {

            List<Contratacao> contratacoes =
                    contratacaoService.listarPorCliente(usuario.getId());

            Map<Long, String> nomesPrestadores =
                    new HashMap<>();

            for (Contratacao contratacao : contratacoes) {

                Usuario prestador =
                        usuarioService.buscarPorId(
                                contratacao.getPrestadorId()
                        );

                if (prestador != null) {
                    nomesPrestadores.put(
                            contratacao.getPrestadorId(),
                            prestador.getNomeCompleto()
                    );
                }
            }

            request.setAttribute(
                    "contratacoes",
                    contratacoes
            );

            request.setAttribute(
                    "nomesPrestadores",
                    nomesPrestadores
            );

            request.getRequestDispatcher(
                    "/views/cliente/client-profile.jsp"
            ).forward(request, response);

        } else if (usuario instanceof Prestador) {

            List<Contratacao> contratacoes =
                    contratacaoService.listarPorPrestador(usuario.getId());

            Map<Long, String> nomesClientes =
                    new HashMap<>();

            for (Contratacao contratacao : contratacoes) {

                Usuario cliente =
                        usuarioService.buscarPorId(
                                contratacao.getClienteId()
                        );

                if (cliente != null) {
                    nomesClientes.put(
                            contratacao.getClienteId(),
                            cliente.getNomeCompleto()
                    );
                }
            }

            request.setAttribute(
                    "contratacoes",
                    contratacoes
            );

            request.setAttribute(
                    "nomesClientes",
                    nomesClientes
            );

            request.getRequestDispatcher(
                    "/views/prestador/provider-profile.jsp"
            ).forward(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/geral/home.jsp"
            );
        }
    }
}