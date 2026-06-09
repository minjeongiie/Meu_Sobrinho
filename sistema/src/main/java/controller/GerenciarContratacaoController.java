package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.ContratacaoServiceImp;
import model.service.interfaces.ContratacaoService;

import java.io.IOException;

@WebServlet("/gerenciar-contratacao")
public class GerenciarContratacaoController extends HttpServlet {

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

        if (!(usuarioLogado instanceof Prestador) &&
                !(usuarioLogado instanceof Cliente)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil"
            );

            return;
        }

        try {
            Long contratacaoId =
                    Long.parseLong(request.getParameter("contratacaoId"));

            String acao =
                    request.getParameter("acao");

            if ("aceitar".equals(acao)) {
                contratacaoService.aceitarContratacao(contratacaoId);

                response.sendRedirect(
                        request.getContextPath()
                                + "/perfil?contratacao=aceita"
                );

                return;
            }

            if ("contraproposta".equals(acao)) {

                Double valor =
                        Double.parseDouble(
                                request.getParameter(
                                        "valorContraproposta"
                                )
                        );

                String mensagem =
                        request.getParameter(
                                "mensagemContraproposta"
                        );

                contratacaoService.enviarContraproposta(
                        contratacaoId,
                        valor,
                        mensagem
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/perfil?contratacao=contraproposta"
                );

                return;
            }

            if ("concluir".equals(acao)) {
                contratacaoService.concluirContratacao(contratacaoId);

                response.sendRedirect(
                        request.getContextPath()
                                + "/perfil?contratacao=concluida"
                );

                return;
            }

            if ("recusar".equals(acao)) {
                contratacaoService.recusarContratacao(contratacaoId);

                response.sendRedirect(
                        request.getContextPath()
                                + "/perfil?contratacao=recusada"
                );

                return;
            }

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil"
            );

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil?erro=dados-invalidos"
            );
        }
    }
}