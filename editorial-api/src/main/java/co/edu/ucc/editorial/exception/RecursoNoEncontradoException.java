package co.edu.ucc.editorial.exception;

/** Se traduce a 404 Not Found. */
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
