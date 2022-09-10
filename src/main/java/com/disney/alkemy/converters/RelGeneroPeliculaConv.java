package com.disney.alkemy.converters;

import com.disney.alkemy.dtos.RelGeneroPeliculaDTO;
import com.disney.alkemy.models.RelGeneroPelicula;

public class RelGeneroPeliculaConv extends AbsConverter<RelGeneroPelicula, RelGeneroPeliculaDTO>{

	@Override
	public RelGeneroPelicula toEntity(RelGeneroPeliculaDTO dto) {
		return RelGeneroPelicula.builder()
				.idRelGeneroPelicula(dto.getIdRelGeneroPelicula())
				.idGenero(dto.getIdGenero())
				.idPeliculaSerie(dto.getIdPeliculaSerie())
				.build();
	}

	@Override
	public RelGeneroPeliculaDTO toDTO(RelGeneroPelicula entity) {
		return RelGeneroPeliculaDTO.builder()
				.idRelGeneroPelicula(entity.getIdRelGeneroPelicula())
				.idGenero(entity.getIdGenero())
				.idPeliculaSerie(entity.getIdPeliculaSerie())
				.build();
	}

}
