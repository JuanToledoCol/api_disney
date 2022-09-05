package com.disney.alkemy.converters;

import java.util.List;

import com.disney.alkemy.dtos.PeliculaSerieDTO;
import com.disney.alkemy.dtos.PersonajeUpdateDTO;
import com.disney.alkemy.models.Personaje;

public class PersonajeUpdateConv {

	public PersonajeUpdateDTO toDto(Personaje personaje, List<PeliculaSerieDTO> listPeliculas) {
		
		return PersonajeUpdateDTO.builder()
				.idPersonaje(personaje.getIdPersonaje())
				.nombrePersonaje(personaje.getNombrePersonaje())
				.imagen(personaje.getImagen())
				.edad(personaje.getEdad())
				.peso(personaje.getPeso())
				.historia(personaje.getHistoria())
				.peliculasAsociadas(listPeliculas)
				.build();
	}

}
