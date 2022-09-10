package com.disney.alkemy.converters;

import com.disney.alkemy.dtos.GeneroDTO;
import com.disney.alkemy.models.Genero;

public class GeneroConv extends AbsConverter<Genero, GeneroDTO>{

	@Override
	public Genero toEntity(GeneroDTO dto) {
		return Genero.builder()
				.idGenero(dto.getIdGenero())
				.nombreGenero(dto.getNombreGenero())
				.imagen(dto.getImagen())
				.peliculasSeries(dto.getPeliculasSeries())
				.build();
	}

	@Override
	public GeneroDTO toDTO(Genero entity) {
		return GeneroDTO.builder()
				.idGenero(entity.getIdGenero())
				.nombreGenero(entity.getNombreGenero())
				.imagen(entity.getImagen())
				.peliculasSeries(entity.getPeliculasSeries())
				.build();
	}

}
