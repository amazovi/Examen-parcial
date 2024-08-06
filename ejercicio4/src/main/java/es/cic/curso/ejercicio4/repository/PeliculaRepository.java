package es.cic.curso.ejercicio4.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.ejercicio4.models.Pelicula;

/*
 * *
 * JpaRepository: Proporciona métodos para realizar operaciones CRUD 
 * y consultas sobre la entidad Pelicula.
 */
 
@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}
