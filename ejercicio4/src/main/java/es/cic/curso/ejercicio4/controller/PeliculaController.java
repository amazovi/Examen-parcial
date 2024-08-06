package es.cic.curso.ejercicio4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.ejercicio4.models.Pelicula;
import es.cic.curso.ejercicio4.service.PeliculaService;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.findById(id);
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping
    public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
        Pelicula newPelicula = peliculaService.save(pelicula);
        return ResponseEntity.ok(newPelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        pelicula.setId(id); 
        Pelicula updatedPelicula = peliculaService.save(pelicula);
        return ResponseEntity.ok(updatedPelicula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}