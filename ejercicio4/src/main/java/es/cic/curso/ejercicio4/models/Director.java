package es.cic.curso.ejercicio4.models;

import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Director {
    private Long id;

    @NotBlank
    @Size(min = 1, max = 20)
    private String nombre;

    @Min(1900)
    private int ano;

    private List<Pelicula> peliculas;

    public Director() {}

   

    public Director(Long id, String nombre,int ano, List<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.ano = ano;
        this.peliculas = peliculas;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getAno() {
        return ano;
    }



    public void setAno(int ano) {
        this.ano = ano;
    }



    @OneToMany(mappedBy = "director")
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}