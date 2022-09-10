package com.disney.alkemy.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.Personaje;

@Repository
public interface PersonajeDao extends JpaRepository<Personaje, Long>{

	public List<Personaje> findByPeliculasSeries_IdPeliculaSerie(Long idPelicula);

	public List<Personaje> findByPeliculasSeries_Titulo(String titulo);

	public List<Personaje> findByEdad(int edad);

	public List<Personaje> findByNombrePersonaje(String nombrePersonaje);

	public List<Personaje> findByPeso(double peso);


}
