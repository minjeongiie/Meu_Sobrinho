package model.service.interfaces;

import model.entity.Usuario;

public interface UsuarioService {

    void cadastrar(Usuario usuario,String confirmarSenha);
}
