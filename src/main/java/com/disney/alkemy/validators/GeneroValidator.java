package com.disney.alkemy.validators;

import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.Genero;

public class GeneroValidator {

	public static void validate(Genero genero) {
		if(genero.getNombreGenero().trim().isEmpty() || genero.getNombreGenero() == null) {
			throw new ValidateServiceException("El nombre del genero no puede estar vacio.");
		}

		if(genero.getImagen().trim().isEmpty() || genero.getImagen() == null) {
			throw new ValidateServiceException("Image cannot be empty");
		}
	}
}
