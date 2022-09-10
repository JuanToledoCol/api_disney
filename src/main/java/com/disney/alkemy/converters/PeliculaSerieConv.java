package com.disney.alkemy.converters;

import com.disney.alkemy.dtos.PeliculaSerieDTO;
import com.disney.alkemy.models.PeliculaSerie;

public class PeliculaSerieConv extends AbsConverter<PeliculaSerie, PeliculaSerieDTO>{

	@Override
	public PeliculaSerie toEntity(PeliculaSerieDTO dto) {
		return PeliculaSerie.builder()
				.idPeliculaSerie(dto.getIdPeliculasSeries())
				.titulo(dto.getTitulo())
				.fechaCreacion(dto.getFechaCreacion())
				.calificacion(dto.getCalificacion())
				.imagen(dto.getImagen())
				.generos(dto.getGeneros())
				.personajes(dto.getPersonajes())
				.build();
	}

	@Override
	public PeliculaSerieDTO toDTO(PeliculaSerie entity) {
		return PeliculaSerieDTO.builder()
				.idPeliculasSeries(entity.getIdPeliculaSerie())
				.titulo(entity.getTitulo())
				.fechaCreacion(entity.getFechaCreacion())
				.calificacion(entity.getCalificacion())
				.imagen(entity.getImagen())
				.generos(entity.getGeneros())
				.personajes(entity.getPersonajes())
				.build();
	}

}
