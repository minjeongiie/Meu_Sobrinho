package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Cliente;
import model.entity.Usuario;
import model.service.implementacoes.ContratacaoServiceImp;
import model.service.interfaces.ContratacaoService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/solicitar-contratacao")
public class SolicitarContratacaoController extends HttpServlet {

    private ContratacaoService contratacaoService;

    @Override
    public void init() {
        contratacaoService = new ContratacaoServiceImp();
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

        if (!(usuarioLogado instanceof Cliente)) {
            request.setAttribute("error", "Apenas clientes podem solicitar contratação.");
            request.getRequestDispatcher("/views/geral/home.jsp").forward(request, response);
            return;
        }

        try {
            Long clienteId = usuarioLogado.getId();
            Long prestadorId = Long.parseLong(request.getParameter("providerId"));

            String descricao = request.getParameter("description");
            String precoParam = request.getParameter("price");
            String dataParam = request.getParameter("requestedDate");

            if (descricao == null || descricao.trim().isEmpty()) {
                request.setAttribute("error", "A descrição é obrigatória.");
                request.getRequestDispatcher("/views/cliente/hire.jsp").forward(request, response);
                return;
            }

            Double preco = null;
            if (precoParam != null && !precoParam.trim().isEmpty()) {
                preco = Double.parseDouble(precoParam);
            }

            LocalDate dataSolicitada = null;
            if (dataParam != null && !dataParam.trim().isEmpty()) {
                dataSolicitada = LocalDate.parse(dataParam);
            }

            contratacaoService.solicitarContratacao(
                    clienteId,
                    prestadorId,
                    descricao,
                    preco,
                    dataSolicitada
            );

            response.sendRedirect(request.getContextPath() + "/perfil-prestador?id=" + prestadorId + "&contratacao=sucesso");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dados inválidos para contratação.");
            request.getRequestDispatcher("/views/cliente/hire.jsp").forward(request, response);
        }
    }
}