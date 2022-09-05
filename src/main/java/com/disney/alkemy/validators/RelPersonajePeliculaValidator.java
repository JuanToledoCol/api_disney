package com.disney.alkemy.validators;

import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.RelPersonajePelicula;

public class RelPersonajePeliculaValidator {

	public static void validate(RelPersonajePelicula rel) {
		if(rel.getIdPeliculaSerie()== null) {
			throw new ValidateServiceException("The idPeli is null");
		}
		
		if(rel.getIdPersonaje()== null) {
			throw new ValidateServiceException("The idPersonaje is null");
		}
	}
}
