package com.disney.alkemy.dtos;

import java.time.LocalDate;

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
public class PeliculaSerieDTO {

	private Long idPeliculasSeries;
	private String titulo;
	private LocalDate fechaCreacion;
	private int calificacion;
	
}
