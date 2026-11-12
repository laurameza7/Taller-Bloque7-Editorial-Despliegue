package co.edu.ucc.editorial.exception;

/** Se traduce a 400 Bad Request cuando no se cumple una regla del caso de uso. */
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
