package com.disney.alkemy.validators;

import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.PeliculaSerie;

public class PeliculaSerieValidator {

	public static void validate(PeliculaSerie peliculaSerie) {
		if(peliculaSerie.getTitulo().trim().isEmpty() || peliculaSerie.getTitulo() == null) {
			throw new ValidateServiceException("Title cannot be empty");
		}

		if(peliculaSerie.getImagen().trim().isEmpty() || peliculaSerie.getImagen() == null) {
			throw new ValidateServiceException("Image cannot be empty");
		}

		if(peliculaSerie.getCalificacion() < 1 && peliculaSerie.getCalificacion() > 5) {
			throw new ValidateServiceException("Rating must be between 1 and 5");
		}
	}
}
