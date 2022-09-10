package com.disney.alkemy.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.disney.alkemy.Impl.PersonajeImpl;
import com.disney.alkemy.converters.PersonajeConv;
import com.disney.alkemy.dtos.PersonajeDTO;
import com.disney.alkemy.models.Personaje;
import com.disney.alkemy.utils.WrapperResponse;

@RestController
public class PersonajeController {

	@Autowired
	private PersonajeImpl impl;

	private PersonajeConv converter = new PersonajeConv();

	@GetMapping(value = "/characters")
	public ResponseEntity<WrapperResponse<List<PersonajeDTO>>> findAll(@RequestParam Map<String, String> parameters){
			List<Personaje> personajes = impl.findAll(parameters);
			List<PersonajeDTO> personajesDto = converter.toDTO(personajes);
			return new WrapperResponse<>(true, "Success", personajesDto).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/characters/{idPersonaje}")
	public  ResponseEntity<?> delete(@PathVariable("idPersonaje") Long idPersonaje){
		impl.delete(idPersonaje);
		return new WrapperResponse<>(true, "Delete Success", null).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/characters")
	public ResponseEntity<WrapperResponse<PersonajeDTO>> create(@RequestBody PersonajeDTO personajeDto){
		Personaje personajeCreate = impl.create(converter.toEntity(personajeDto));
		PersonajeDTO personajeCreateDto = converter.toDTO(personajeCreate);
		return new WrapperResponse<>(true, "Successfully created character", personajeCreateDto).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/characters")
	public ResponseEntity<WrapperResponse<PersonajeDTO>> update(@RequestBody PersonajeDTO personajeDto){
		Personaje personajeUp = impl.update(converter.toEntity(personajeDto));
		PersonajeDTO personajeUpDto = converter.toDTO(personajeUp);
		return new WrapperResponse<>(true, "Successfully updated character", personajeUpDto).createResponse(HttpStatus.OK);
	}
}
