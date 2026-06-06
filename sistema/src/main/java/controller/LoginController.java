package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.UsuarioService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() {

        usuarioService =
                new UsuarioServiceImp();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String senha =
                request.getParameter("password");

        try {

            Usuario usuario =
                    usuarioService.autenticar(
                            email,
                            senha
                    );

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "usuarioLogado",
                    usuario
            );

            if (usuario instanceof Cliente) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/views/geral/home.jsp"
                );

            } else if (usuario instanceof Prestador) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/views/geral/home.jsp"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/views/geral/login.jsp"
                );
            }

        } catch (IllegalArgumentException e) {

            request.setAttribute(
                    "erro",
                    e.getMessage()
            );

            request.getRequestDispatcher(
                    "/views/login.jsp"
            ).forward(request, response);
        }
    }
}
