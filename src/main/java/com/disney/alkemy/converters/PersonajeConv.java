package com.disney.alkemy.converters;

import com.disney.alkemy.dtos.PersonajeDTO;
import com.disney.alkemy.models.Personaje;

public class PersonajeConv extends AbsConverter<Personaje, PersonajeDTO>{

	@Override
	public Personaje toEntity(PersonajeDTO dto) {
		return Personaje.builder()
				.idPersonaje(dto.getIdPersonaje())
				.nombrePersonaje(dto.getNombrePersonaje())
				.imagen(dto.getImagen())
				.edad(dto.getEdad())
				.peso(dto.getPeso())
				.historia(dto.getHistoria()).build();
	}

	@Override
	public PersonajeDTO toDTO(Personaje entity) {
		return PersonajeDTO.builder()
				.idPersonaje(entity.getIdPersonaje())
				.nombrePersonaje(entity.getNombrePersonaje())
				.imagen(entity.getImagen())
				.edad(entity.getEdad())
				.peso(entity.getPeso())
				.historia(entity.getHistoria()).build();
	}

}
