package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Categoria;
import model.entity.Cliente;
import model.entity.Usuario;
import model.service.implementacoes.CategoriaServiceImp;
import model.service.implementacoes.SolicitacaoServicoServiceImp;
import model.service.interfaces.CategoriaService;
import model.service.interfaces.SolicitacaoServicoService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/publicar-solicitacao")
public class PublicarSolicitacaoController extends HttpServlet {

    private SolicitacaoServicoService solicitacaoService;
    private CategoriaService categoriaService;

    @Override
    public void init() {
        solicitacaoService = new SolicitacaoServicoServiceImp();
        categoriaService = new CategoriaServiceImp();
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
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            String valorParam = request.getParameter("valorEstimado");
            String categoriaParam = request.getParameter("categoriaId");
            String dataParam = request.getParameter("dataDesejada");

            Double valorEstimado = null;

            if (valorParam != null &&
                    !valorParam.trim().isEmpty()) {

                valorEstimado =
                        Double.parseDouble(valorParam);
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

            LocalDate dataDesejada =
                    LocalDate.parse(dataParam);

            solicitacaoService.publicarSolicitacao(
                    usuario.getId(),
                    titulo,
                    descricao,
                    valorEstimado,
                    categoria,
                    dataDesejada
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/listar-solicitacoes"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher(
                    "/views/cliente/publicar-solicitacao.jsp"
            ).forward(request, response);
        }
    }
}