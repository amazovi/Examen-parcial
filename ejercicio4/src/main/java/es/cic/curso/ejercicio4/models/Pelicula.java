package es.cic.curso.ejercicio4.models;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Pelicula {

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String titulo;

    @Min(value = 1900, message = "El año debe ser como mínimo 1900")
    private int ano;

    private Director director;


    public Pelicula() {
    }


    public Pelicula(String titulo, int ano, Director director) {
        this.titulo = titulo;
        this.ano = ano;
        this.director = director;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getAno() {
        return ano;
    }


    public void setAno(int ano) {
        this.ano = ano;
    }


   @OneToOne
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
    
