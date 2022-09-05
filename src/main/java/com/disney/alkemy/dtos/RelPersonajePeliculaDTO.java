package com.disney.alkemy.dtos;

import com.disney.alkemy.models.PeliculaSerie;
import com.disney.alkemy.models.Personaje;

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
public class RelPersonajePeliculaDTO {

	private Long idRelPersonajePelicula;
	private Personaje idPersonaje;
	private PeliculaSerie idPeliculasSeries;
}
