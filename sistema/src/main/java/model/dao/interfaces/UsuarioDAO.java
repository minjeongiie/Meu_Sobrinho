package model.dao.interfaces;

import model.entity.Usuario;

public interface UsuarioDAO {

    void salvar(Usuario usuario);

    Usuario buscarPorEmail(String email);
}
