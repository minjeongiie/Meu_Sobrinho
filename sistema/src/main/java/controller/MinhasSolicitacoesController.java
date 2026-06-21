package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Cliente;
import model.entity.PropostaServico;
import model.entity.SolicitacaoServico;
import model.entity.Usuario;
import model.service.implementacoes.PropostaServicoServiceImp;
import model.service.implementacoes.SolicitacaoServicoServiceImp;
import model.service.interfaces.PropostaServicoService;
import model.service.interfaces.SolicitacaoServicoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/minhas-solicitacoes")
public class MinhasSolicitacoesController extends HttpServlet {

    private SolicitacaoServicoService solicitacaoService;
    private PropostaServicoService propostaService;

    @Override
    public void init() {
        solicitacaoService = new SolicitacaoServicoServiceImp();
        propostaService = new PropostaServicoServiceImp();
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

        if (!(usuario instanceof Cliente)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil"
            );

            return;
        }

        List<SolicitacaoServico> solicitacoes =
                solicitacaoService.listarPorCliente(usuario.getId());

        Map<Long, List<PropostaServico>> propostasPorSolicitacao =
                new HashMap<>();

        for (SolicitacaoServico solicitacao : solicitacoes) {

            List<PropostaServico> propostas =
                    propostaService.listarPorSolicitacao(
                            solicitacao.getId()
                    );

            propostasPorSolicitacao.put(
                    solicitacao.getId(),
                    propostas
            );
        }

        request.setAttribute(
                "solicitacoes",
                solicitacoes
        );

        request.setAttribute(
                "propostasPorSolicitacao",
                propostasPorSolicitacao
        );

        request.getRequestDispatcher(
                "/views/cliente/minhas-solicitacoes.jsp"
        ).forward(request, response);
    }
}