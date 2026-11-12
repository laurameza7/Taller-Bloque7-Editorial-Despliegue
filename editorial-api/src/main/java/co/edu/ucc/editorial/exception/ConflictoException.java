package co.edu.ucc.editorial.exception;

/** Se traduce a 409 Conflict (ej. ISBN repetido). */
public class ConflictoException extends RuntimeException {
    public ConflictoException(String mensaje) {
        super(mensaje);
    }
}
