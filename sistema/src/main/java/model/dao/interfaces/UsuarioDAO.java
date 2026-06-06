package model.dao.interfaces;

import model.entity.Prestador;
import model.entity.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void salvar(Usuario usuario);

    Usuario buscarPorEmail(String email);

    Usuario buscarPorId(Long id);

    List<Prestador> listarPrestadores();

    List<Prestador> buscarPrestadores(String termo);

}
