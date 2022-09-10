package com.disney.alkemy.dtos;

import java.util.List;

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
public class GeneroDTO {

	private Long idGenero;
	private String nombreGenero;
	private String imagen;

	private List<PeliculaSerie> peliculasSeries;
}
