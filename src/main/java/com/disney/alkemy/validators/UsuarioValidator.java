package com.disney.alkemy.validators;

import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.Usuario;

public class UsuarioValidator {

	public static void validate(Usuario usuario) {
		if(usuario.getUsuario().trim().isEmpty() || usuario.getUsuario() == null) {
			throw new ValidateServiceException("User cannot be empty");
		}

		if(usuario.getClave().trim().isEmpty() || usuario.getClave() == null) {
			throw new ValidateServiceException("User cannot be empty");
		}
	}
}
