package com.disney.alkemy.validators;

import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.Personaje;

public class PersonajeValidator {

	public static void validate(Personaje personaje) {
		if(personaje.getNombrePersonaje().trim().isEmpty() || personaje.getNombrePersonaje() == null) {
			throw new ValidateServiceException("Name cannot be empty");
		}
		
		if(personaje.getImagen().trim().isEmpty() || personaje.getImagen() == null) {
			throw new ValidateServiceException("Image cannot be empty");
		}
		
		if(personaje.getHistoria().trim().isEmpty() || personaje.getHistoria() == null) {
			throw new ValidateServiceException("History cannot be empty");
		}
		
		if(personaje.getEdad() <= 0) {
			throw new ValidateServiceException("Age must be greater than 0");
		}
		
		if(personaje.getPeso() <= 0) {
			throw new ValidateServiceException("Weight must be greater than 0");
		}
	}
}
