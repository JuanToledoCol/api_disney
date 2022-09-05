package com.disney.alkemy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.disney.alkemy.converters.PeliculaSerieConv;
import com.disney.alkemy.converters.PersonajeConv;
import com.disney.alkemy.converters.PersonajeUpdateConv;
import com.disney.alkemy.dtos.PeliculaSerieDTO;
import com.disney.alkemy.dtos.PersonajeDTO;
import com.disney.alkemy.dtos.PersonajeUpdateDTO;
import com.disney.alkemy.models.PeliculaSerie;
import com.disney.alkemy.models.Personaje;
import com.disney.alkemy.repositoriesImpl.PersonajeImpl;
import com.disney.alkemy.utils.WrapperResponse;

@RestController
public class PersonajeController {

	@Autowired
	private PersonajeImpl impl;
	
	private PersonajeConv converter = new PersonajeConv();
	private PeliculaSerieConv conv = new PeliculaSerieConv();
	private PersonajeUpdateConv converterUp = new PersonajeUpdateConv();
	
	@GetMapping(value = "/personajes")
	public ResponseEntity<WrapperResponse<List<PersonajeDTO>>> findAll(){
		List<Personaje> personajes = impl.findAll();
		List<PersonajeDTO> personajesDto = converter.toDTO(personajes);
		return new WrapperResponse<>(true, "Success", personajesDto).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value = "personajes/{idPersonaje}")
	public ResponseEntity<WrapperResponse<PersonajeDTO>> findById(@PathVariable("idPersonaje") Long idPersonaje){
		Personaje personaje = impl.findById(idPersonaje);
		PersonajeDTO personajeDto = converter.toDTO(personaje);
		return new WrapperResponse<>(true, "Succes", personajeDto).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value = "personaje/{titulo}")
	public ResponseEntity<WrapperResponse<List<PersonajeDTO>>> findByTitulo(@PathVariable("titulo") String titulo){
		List<Personaje> peli = impl.findByTituloSerie(titulo);
		List<PersonajeDTO> peliDto = converter.toDTO(peli);
		return new WrapperResponse<>(true, "Succes", peliDto).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value = "personajeS/{personaje}")
	public ResponseEntity<WrapperResponse<List<PeliculaSerieDTO>>> findSerieByPersonaje(@PathVariable("personaje") String personaje){
		List<PeliculaSerie> peli = impl.findSerieByPersonaje(personaje);
		List<PeliculaSerieDTO> peliDto = conv.toDTO(peli);
		return new WrapperResponse<>(true, "Succes", peliDto).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/personajes/{idPersonaje}")
	public  ResponseEntity<?> delete(@PathVariable("idPersonaje") Long idPersonaje){
		impl.delete(idPersonaje);
		return new WrapperResponse<>(true, "Delete Success", null).createResponse(HttpStatus.OK);
	}
	
	@PostMapping(value = "/personajes")
	public ResponseEntity<WrapperResponse<PersonajeDTO>> create(@RequestBody PersonajeDTO personajeDto){
		Personaje personajeCreate = impl.create(converter.toEntity(personajeDto));
		PersonajeDTO personajeCreateDto = converter.toDTO(personajeCreate);
		return new WrapperResponse<>(true, "Successfully created character", personajeCreateDto).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/personajes")
	public ResponseEntity<WrapperResponse<PersonajeUpdateDTO>> update(@RequestBody PersonajeDTO personajeDto){
		Personaje personajeUp = impl.update(converter.toEntity(personajeDto));
		List<PeliculaSerie>  peliSeri = impl.findSerieByPersonaje(personajeUp.getNombrePersonaje());
		List<PeliculaSerieDTO> peliDto = conv.toDTO(peliSeri);
		PersonajeUpdateDTO perUpDto = converterUp.toDto(personajeUp, peliDto);
		
		return new WrapperResponse<>(true, "Successfully updated character", perUpDto).createResponse(HttpStatus.OK);
	}
}
