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

import es.cic.curso.ejercicio4.models.Director;
import es.cic.curso.ejercicio4.service.DirectorService;

@RestController
@RequestMapping("/api/directores")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public List<Director> getAllDirectores() {
        return directorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Long id) {
        Director director = directorService.findById(id);
        return ResponseEntity.ok(director);
    }

    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        Director newDirector = directorService.save(director);
        return ResponseEntity.ok(newDirector);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(@PathVariable Long id, @RequestBody Director director) {
        director.setId(id); 
        Director updatedDirector = directorService.save(director);
        return ResponseEntity.ok(updatedDirector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
