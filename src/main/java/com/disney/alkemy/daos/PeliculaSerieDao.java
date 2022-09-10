package com.disney.alkemy.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.PeliculaSerie;

@Repository
public interface PeliculaSerieDao extends JpaRepository<PeliculaSerie, Long> {

	public List<PeliculaSerie> findByGeneros_IdGenero(Long Idgenero);

	public List<PeliculaSerie> findByTitulo(String nombre);

	public List<PeliculaSerie> findAllByOrderByIdPeliculaSerieAsc();

	public List<PeliculaSerie> findAllByOrderByIdPeliculaSerieDesc();


}
