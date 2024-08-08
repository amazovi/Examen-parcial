
package es.cic.curso;

public class Mueble {
    private String material;
    private double peso;

    public Mueble(String material, double peso) {
        this.material = material;
        this.peso = peso;
    }


    public Mueble() {
    }


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}


