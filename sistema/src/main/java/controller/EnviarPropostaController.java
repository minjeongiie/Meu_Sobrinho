package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.PropostaServicoServiceImp;
import model.service.interfaces.PropostaServicoService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/enviar-proposta")
public class EnviarPropostaController extends HttpServlet {

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

        if (!(usuario instanceof Prestador)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/listar-solicitacoes"
            );

            return;
        }

        try {
            Long solicitacaoId =
                    Long.parseLong(request.getParameter("solicitacaoId"));

            Double valorProposto =
                    Double.parseDouble(request.getParameter("valorProposto"));

            String descricao =
                    request.getParameter("descricao");

            LocalDate prazoConclusao =
                    LocalDate.parse(request.getParameter("prazoConclusao"));

            propostaService.enviarProposta(
                    solicitacaoId,
                    usuario.getId(),
                    valorProposto,
                    descricao,
                    prazoConclusao
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/listar-solicitacoes"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher(
                    "/views/geral/solicitacoes.jsp"
            ).forward(request, response);
        }
    }
}