package main.java.es.cic.curso;

/*
 * Con esta clase defino la estructura con una variable numérica y un nombre que solo necesitamos para los números pares
 */
public class Nodo {
    private int valor;
    private String nombre;

    public Nodo(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }
}