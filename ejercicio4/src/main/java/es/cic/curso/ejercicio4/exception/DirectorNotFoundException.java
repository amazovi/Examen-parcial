package es.cic.curso.ejercicio4.exception;

public class DirectorNotFoundException extends RuntimeException {

    // Constructor vacío
    public DirectorNotFoundException() {
        super();
    }

    public DirectorNotFoundException(String mensaje) {
        super(mensaje);

    }

    public DirectorNotFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}