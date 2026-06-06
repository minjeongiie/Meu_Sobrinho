package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.UsuarioService;

import java.io.IOException;

@WebServlet("/buscar-prestadores")
public class ListarPrestadoresController extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() {

        usuarioService =
                new UsuarioServiceImp();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setAttribute(
                "results",
                usuarioService.listarPrestadores()
        );

        request.getRequestDispatcher(
                "/views/geral/search.jsp"
        ).forward(
                request,
                response
        );
    }
}
