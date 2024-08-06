package es.cic.curso.ejercicio4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.ejercicio4.models.Director;

/* *
 * JpaRepository: Proporciona métodos para realizar operaciones CRUD 
 * y consultas sobre la entidad Director.
 */
@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}