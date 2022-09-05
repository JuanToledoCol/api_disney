package com.disney.alkemy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonajeDTO {

	private Long idPersonaje;
	private String nombrePersonaje;
	private String imagen;
	private int edad;
	private double peso;
	private String historia;
}
