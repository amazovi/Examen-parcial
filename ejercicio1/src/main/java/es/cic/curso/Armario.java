package main.java.es.cic.curso;

public class Armario extends Mueble implements Compuertas {
    private int numCompuertas;

    public Armario(String material, double peso, int numCompuertas) {
        super(material, peso);
        this.numCompuertas = numCompuertas;
    }

    public int getNumCompuertas() {
        return numCompuertas;
    }

    public void setNumCompuertas(int numCompuertas) {
        this.numCompuertas = numCompuertas;
    }

    @Override
    public void abrirCompuertas() {
        System.out.println("Abriendo las compuertas del armario.");
    }

    @Override
    public void cerrarCompuertas() {
        System.out.println("Cerrando las compuertas del armario.");
    }

    @Override
    public String toString() {
        return "Armario [numCompuertas=" + numCompuertas + ", " + super.toString() + "]";
    }
}
