package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;

import java.io.IOException;

@WebServlet("/perfil")
public class PerfilController extends HttpServlet {

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

        if (usuario instanceof Cliente) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/cliente/client-profile.jsp"
            );

        } else if (usuario instanceof Prestador) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/prestador/provider-profile.jsp"
            );

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/views/geral/home.jsp"
            );
        }
    }
}