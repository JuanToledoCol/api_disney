package com.disney.alkemy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long>{

	public List<Personaje> findByPeliculasSeries_Titulo(String titulo);
	
}
