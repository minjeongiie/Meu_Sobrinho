package model.service.interfaces;

import model.entity.Prestador;
import model.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    void cadastrar(Usuario usuario, String confirmarSenha);

    Usuario autenticar(String email, String senha);

    Usuario buscarPorId(Long id);

    List<Prestador> listarPrestadores();
}