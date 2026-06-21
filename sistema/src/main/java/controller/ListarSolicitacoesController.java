package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.SolicitacaoServico;
import model.service.implementacoes.SolicitacaoServicoServiceImp;
import model.service.interfaces.SolicitacaoServicoService;

import java.io.IOException;
import java.util.List;

@WebServlet("/listar-solicitacoes")
public class ListarSolicitacoesController extends HttpServlet {

    private SolicitacaoServicoService solicitacaoService;

    @Override
    public void init() {
        solicitacaoService = new SolicitacaoServicoServiceImp();
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

        List<SolicitacaoServico> solicitacoes =
                solicitacaoService.listarAbertas();

        request.setAttribute(
                "solicitacoes",
                solicitacoes
        );

        request.getRequestDispatcher(
                "/views/geral/solicitacoes.jsp"
        ).forward(request, response);
    }
}