package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Cliente;
import model.entity.Usuario;
import model.service.implementacoes.PropostaServicoServiceImp;
import model.service.interfaces.PropostaServicoService;

import java.io.IOException;

@WebServlet("/responder-proposta")
public class ResponderPropostaController extends HttpServlet {

    private PropostaServicoService propostaService;

    @Override
    public void init() {
        propostaService = new PropostaServicoServiceImp();
    }

    @Override
    protected void doPost(
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

        if (!(usuario instanceof Cliente)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil"
            );

            return;
        }

        try {
            Long propostaId =
                    Long.parseLong(request.getParameter("propostaId"));

            String acao =
                    request.getParameter("acao");

            if ("ACEITAR".equals(acao)) {
                propostaService.aceitarProposta(propostaId);

            } else if ("RECUSAR".equals(acao)) {
                propostaService.recusarProposta(propostaId);

            } else {
                throw new IllegalArgumentException(
                        "Ação inválida."
                );
            }

            response.sendRedirect(
                    request.getContextPath()
                            + "/minhas-solicitacoes"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            request.getRequestDispatcher(
                    "/minhas-solicitacoes"
            ).forward(request, response);
        }
    }
}