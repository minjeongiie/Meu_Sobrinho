package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Cliente;
import model.entity.Prestador;
import model.entity.Usuario;
import model.service.implementacoes.UsuarioServiceImp;
import model.service.interfaces.UsuarioService;

import java.io.IOException;

@WebServlet("/cadastro")
public class UsuarioController extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() {
        usuarioService = new UsuarioServiceImp();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipoUsuario = request.getParameter("tipoUsuario");

        String senha = request.getParameter("password");
        String confirmarSenha =
                request.getParameter("password2");

        Usuario usuario;

        if (tipoUsuario.equals("CLIENTE")) {

            Cliente cliente = new Cliente();

            cliente.setCpf(request.getParameter("cpf"));

            usuario = cliente;

        } else {

            Prestador prestador = new Prestador();

            prestador.setCpfCnpj(request.getParameter("doc"));
            prestador.setCelular(request.getParameter("celular"));

            usuario = prestador;
        }

        usuario.setNomeCompleto(request.getParameter("name"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setSenha(senha);

        usuario.setPerguntaSeguranca(request.getParameter("securityQuestion"));
        usuario.setRespostaSeguranca(request.getParameter("securityAnswer"));

        usuarioService.cadastrar(usuario,confirmarSenha);

        response.sendRedirect("views/geral/login.jsp");
    }
}