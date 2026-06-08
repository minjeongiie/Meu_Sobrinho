package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Prestador;
import model.service.implementacoes.AvaliacaoServiceImp;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.AvaliacaoService;
import model.service.interfaces.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/buscar-prestadores")
public class BuscarPrestadoresController extends HttpServlet {

    private UsuarioService usuarioService;
    private AvaliacaoService avaliacaoService;

    @Override
    public void init() {
        usuarioService = new UsuarioServiceImp();
        avaliacaoService = new AvaliacaoServiceImp();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String termo = request.getParameter("q");

        List<Prestador> resultados =
                usuarioService.buscarPrestadores(termo);

        Map<Long, Double> mediasAvaliacoes =
                new HashMap<>();

        Map<Long, Integer> totaisAvaliacoes =
                new HashMap<>();

        for (Prestador prestador : resultados) {

            mediasAvaliacoes.put(
                    prestador.getId(),
                    avaliacaoService.calcularMediaPorPrestador(
                            prestador.getId()
                    )
            );

            totaisAvaliacoes.put(
                    prestador.getId(),
                    avaliacaoService.contarAvaliacoesPorPrestador(
                            prestador.getId()
                    )
            );
        }

        request.setAttribute("results", resultados);
        request.setAttribute("mediasAvaliacoes", mediasAvaliacoes);
        request.setAttribute("totaisAvaliacoes", totaisAvaliacoes);

        request.getRequestDispatcher(
                "/views/geral/search.jsp"
        ).forward(request, response);
    }
}