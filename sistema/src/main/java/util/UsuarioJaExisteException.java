package util;

public class UsuarioJaExisteException extends RuntimeException {

    public UsuarioJaExisteException(String mensagem) {
        super(mensagem);
    }
}
