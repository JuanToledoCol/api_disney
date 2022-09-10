package com.disney.alkemy.converters;

import com.disney.alkemy.dtos.RelPersonajePeliculaDTO;
import com.disney.alkemy.models.RelPersonajePelicula;

public class RelPersonajePeliculaConv extends AbsConverter<RelPersonajePelicula, RelPersonajePeliculaDTO>{

	@Override
	public RelPersonajePelicula toEntity(RelPersonajePeliculaDTO dto) {
		return RelPersonajePelicula.builder()
				.idRelPersonajePelicula(dto.getIdRelPersonajePelicula())
				.idPersonaje(dto.getIdPersonaje())
				.idPeliculaSerie(dto.getIdPeliculaSerie())
				.build();
	}

	@Override
	public RelPersonajePeliculaDTO toDTO(RelPersonajePelicula entity) {
		return RelPersonajePeliculaDTO.builder()
				.idRelPersonajePelicula(entity.getIdRelPersonajePelicula())
				.idPersonaje(entity.getIdPersonaje())
				.idPeliculaSerie(entity.getIdPeliculaSerie())
				.build();
	}

}
