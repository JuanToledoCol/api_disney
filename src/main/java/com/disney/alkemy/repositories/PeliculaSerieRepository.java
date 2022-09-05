package com.disney.alkemy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.PeliculaSerie;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {
	
	public List<PeliculaSerie> findByPersonajes_NombrePersonaje(String nombre);

}
