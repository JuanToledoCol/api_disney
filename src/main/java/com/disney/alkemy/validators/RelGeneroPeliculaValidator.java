package com.disney.alkemy.validators;

import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.RelGeneroPelicula;

public class RelGeneroPeliculaValidator {

	public static void validate(RelGeneroPelicula rel) {
		if(rel.getIdPeliculaSerie()== null) {
			throw new ValidateServiceException("The idPeli is null");
		}

		if(rel.getIdGenero()== null) {
			throw new ValidateServiceException("The idGener is null");
		}
	}
}
