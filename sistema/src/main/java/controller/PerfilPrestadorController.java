package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Avaliacao;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.AvaliacaoServiceImp;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.AvaliacaoService;
import model.service.interfaces.UsuarioService;

import java.io.IOException;
import java.util.List;

@WebServlet("/perfil-prestador")
public class PerfilPrestadorController extends HttpServlet {

    private UsuarioService usuarioService;
    private AvaliacaoService avaliacaoService;

    @Override
    public void init() {

        usuarioService =
                new UsuarioServiceImp();

        avaliacaoService =
                new AvaliacaoServiceImp();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String idParam =
                request.getParameter("id");

        if (idParam == null
                || idParam.trim().isEmpty()) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "ID não informado"
            );

            return;
        }

        Long id;

        try {

            id = Long.parseLong(idParam);

        } catch (NumberFormatException e) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "ID inválido"
            );

            return;
        }

        Usuario usuario =
                usuarioService.buscarPorId(id);

        if (usuario == null) {

            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Prestador não encontrado"
            );

            return;
        }

        if (!(usuario instanceof Prestador)) {

            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Usuário não é um prestador"
            );

            return;
        }

        Prestador prestador =
                (Prestador) usuario;

        if (!prestador.isPerfilPublico()) {

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Este perfil é privado"
            );

            return;
        }

        Usuario usuarioLogado =
                (Usuario) request.getSession()
                        .getAttribute("usuarioLogado");

        List<Avaliacao> avaliacoes =
                avaliacaoService.listarPorPrestador(
                        prestador.getId()
                );

        double mediaAvaliacoes =
                avaliacaoService.calcularMediaPorPrestador(prestador.getId());

        int totalAvaliacoes =
                avaliacaoService.contarAvaliacoesPorPrestador(prestador.getId());

        request.setAttribute(
                "ehCliente",
                usuarioLogado instanceof Cliente
        );

        request.setAttribute(
                "prestador",
                prestador
        );

        request.setAttribute(
                "avaliacoes",
                avaliacoes
        );

        request.setAttribute(
                "mediaAvaliacoes",
                mediaAvaliacoes
        );

        request.setAttribute(
                "totalAvaliacoes",
                totalAvaliacoes
        );

        request.getRequestDispatcher(
                "/views/prestador/profile.jsp"
        ).forward(
                request,
                response
        );
    }
}