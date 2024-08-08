package es.cic.curso;

public class Cama extends Mueble {
    private String tamaño;

    public Cama(String material, double peso, String tamaño) {
        super(material, peso);
        this.tamaño = tamaño;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    
}