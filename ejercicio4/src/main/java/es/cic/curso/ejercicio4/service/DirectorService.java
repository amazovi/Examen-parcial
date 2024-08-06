package es.cic.curso.ejercicio4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.ejercicio4.exception.DirectorNotFoundException;
import es.cic.curso.ejercicio4.models.Director;
import es.cic.curso.ejercicio4.repository.DirectorRepository;

    @Service
    public class DirectorService {
    
        @Autowired
        private DirectorRepository directorRepository;
    
        public List<Director> findAll() {
            return directorRepository.findAll();
        }
    
        public Director findById(Long id) {
            return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Director no encontrado con id: " + id));
        }
    
        public Director save(Director director) {
            return directorRepository.save(director);
        }
    
        public void delete(Long id) {
            if (!directorRepository.existsById(id)) {
                throw new DirectorNotFoundException("Director no encontrado con id: " + id);
            }
            directorRepository.deleteById(id);
        }
    }