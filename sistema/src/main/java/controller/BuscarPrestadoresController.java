package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Prestador;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.UsuarioService;

import java.io.IOException;
import java.util.List;

@WebServlet("/buscar-prestadores")
public class BuscarPrestadoresController extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() {
        usuarioService = new UsuarioServiceImp();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String termo = request.getParameter("q");

        List<Prestador> resultados =
                usuarioService.buscarPrestadores(termo);

        request.setAttribute("results", resultados);

        request.getRequestDispatcher(
                "/views/geral/search.jsp"
        ).forward(request, response);
    }
}