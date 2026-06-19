package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.DashboardPrestador;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.DashboardPrestadorServiceImp;
import model.service.interfaces.DashboardPrestadorService;

import java.io.IOException;

@WebServlet("/dashboard-prestador")
public class DashboardPrestadorController extends HttpServlet {

    private DashboardPrestadorService dashboardPrestadorService;

    @Override
    public void init() {
        dashboardPrestadorService = new DashboardPrestadorServiceImp();
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

        if (!(usuario instanceof Prestador)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/perfil"
            );

            return;
        }

        DashboardPrestador dashboard =
                dashboardPrestadorService.gerarDashboard(
                        usuario.getId()
                );

        request.setAttribute(
                "dashboard",
                dashboard
        );

        request.getRequestDispatcher(
                "/views/prestador/dashboard.jsp"
        ).forward(request, response);
    }
}