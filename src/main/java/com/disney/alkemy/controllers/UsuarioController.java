package com.disney.alkemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.disney.alkemy.Impl.UsuarioImpl;
import com.disney.alkemy.converters.UsuarioConv;
import com.disney.alkemy.dtos.LoginResponseDTO;
import com.disney.alkemy.dtos.UsuarioDTO;
import com.disney.alkemy.models.Usuario;
import com.disney.alkemy.utils.WrapperResponse;

@RestController
public class UsuarioController {

	@Autowired 
	private UsuarioImpl impl;
	
	private UsuarioConv converter = new UsuarioConv();
	
	@PostMapping(value = "/signup")
	public ResponseEntity<WrapperResponse<UsuarioDTO>> signUp(@RequestBody UsuarioDTO usuario) {
		Usuario usuarioN = impl.signUp(converter.toEntity(usuario));
		UsuarioDTO usuarioDto = converter.toDTO(usuarioN);
		return new WrapperResponse<>(true, "El registro ha sido exitoso.", usuarioDto).createResponse(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody UsuarioDTO request) {
		LoginResponseDTO response = impl.login(request);
		return new WrapperResponse<>(true, "Ingreso exitoso", response).createResponse(HttpStatus.OK);
	}
}
