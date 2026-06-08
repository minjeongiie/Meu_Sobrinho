package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Cliente;
import model.entity.Usuario;
import model.service.implementacoes.AvaliacaoServiceImp;
import model.service.interfaces.AvaliacaoService;

import java.io.IOException;

@WebServlet("/avaliacao")
public class AvaliacaoController extends HttpServlet {

    private AvaliacaoService avaliacaoService;

    @Override
    public void init() {
        avaliacaoService = new AvaliacaoServiceImp();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("usuarioLogado") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/geral/login.jsp"
            );

            return;
        }

        Usuario usuarioLogado =
                (Usuario) session.getAttribute("usuarioLogado");

        if (!(usuarioLogado instanceof Cliente)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil"
            );

            return;
        }

        try {
            Long contratacaoId =
                    Long.parseLong(
                            request.getParameter("contratacaoId")
                    );

            Long prestadorId =
                    Long.parseLong(
                            request.getParameter("prestadorId")
                    );

            int nota =
                    Integer.parseInt(
                            request.getParameter("nota")
                    );

            String comentario =
                    request.getParameter("comentario");

            avaliacaoService.avaliarContratacao(
                    contratacaoId,
                    usuarioLogado.getId(),
                    prestadorId,
                    nota,
                    comentario
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil?avaliacao=sucesso"
            );

        } catch (IllegalArgumentException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil?avaliacao=erro"
            );
        }
    }
}