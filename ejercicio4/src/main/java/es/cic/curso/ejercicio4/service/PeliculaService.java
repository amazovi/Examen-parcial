package es.cic.curso.ejercicio4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.ejercicio4.exception.PeliculaNotFoundExtension;
import es.cic.curso.ejercicio4.models.Pelicula;
import es.cic.curso.ejercicio4.repository.PeliculaRepository;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

    public Pelicula findById(Long id) {
        return peliculaRepository.findById(id)
            .orElseThrow(() -> new PeliculaNotFoundExtension("Pelicula no encontrada con id: " + id));
    }

    public Pelicula save(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void delete(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new PeliculaNotFoundExtension("Pelicula no encontrada con id: " + id);
        }
        peliculaRepository.deleteById(id);
    }
}