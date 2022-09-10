package com.disney.alkemy.dtos;

import com.disney.alkemy.models.Genero;
import com.disney.alkemy.models.PeliculaSerie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelGeneroPeliculaDTO {

	private Long idRelGeneroPelicula;
	private Genero idGenero;
	private PeliculaSerie idPeliculaSerie;
}
