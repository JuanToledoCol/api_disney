package com.disney.alkemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disney.alkemy.Impl.UsuarioImpl;
import com.disney.alkemy.converters.UsuarioConv;
import com.disney.alkemy.dtos.LoginResponseDTO;
import com.disney.alkemy.dtos.UsuarioDTO;
import com.disney.alkemy.models.Usuario;
import com.disney.alkemy.utils.WrapperResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioImpl impl;

	private UsuarioConv converter = new UsuarioConv();

	// Documentation
	@Operation(summary = "Registrar un usuario", description = "El registro de usuario requiere del nombre de usuario y una contraseña.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "SignUp success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	// Mapping
	@PostMapping(value = "/signup")
	public ResponseEntity<WrapperResponse<UsuarioDTO>> signUp(@RequestBody UsuarioDTO usuario) {
		Usuario usuarioN = impl.signUp(converter.toEntity(usuario));
		UsuarioDTO usuarioDto = converter.toDTO(usuarioN);
		return new WrapperResponse<>(true, "El registro ha sido exitoso.", usuarioDto)
				.createResponse(HttpStatus.CREATED);
	}

	// Documentation
	@Operation(summary = "Iniciar sesion", description = "El inicio de sesión requiere de un usuario y una clave registrada en la BD"
			+ "</br> Si el inicio de sesión es correcto, tendremos un token para poder acceder a los demás servicios.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	// Mapping
	@PostMapping(value = "/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody UsuarioDTO request) {
		LoginResponseDTO response = impl.login(request);
		return new WrapperResponse<>(true, "Ingreso exitoso", response).createResponse(HttpStatus.OK);
	}
}
