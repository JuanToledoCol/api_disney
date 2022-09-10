package com.disney.alkemy.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.Genero;

@Repository
public interface GeneroDao extends JpaRepository<Genero, Long> {

	public List<Genero> findByPeliculasSeries_IdPeliculaSerie(Long idPelicula);

	public List<Genero> findByNombreGenero(String Genero);
}
