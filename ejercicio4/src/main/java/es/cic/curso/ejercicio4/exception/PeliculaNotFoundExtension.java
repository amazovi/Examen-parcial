package es.cic.curso.ejercicio4.exception;


public class PeliculaNotFoundExtension extends RuntimeException {

    // Constructor vacío
    public PeliculaNotFoundExtension() {
        super();
    }

    public PeliculaNotFoundExtension(String mensaje) {
        super(mensaje);

    }

    public PeliculaNotFoundExtension(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}