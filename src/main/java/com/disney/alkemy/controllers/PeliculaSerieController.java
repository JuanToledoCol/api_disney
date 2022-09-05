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
import com.disney.alkemy.dtos.PeliculaSerieDTO;
import com.disney.alkemy.models.PeliculaSerie;
import com.disney.alkemy.repositoriesImpl.PeliculaSerieImpl;
import com.disney.alkemy.utils.WrapperResponse;

@RestController
public class PeliculaSerieController {

	@Autowired
	private PeliculaSerieImpl impl;
	
	private PeliculaSerieConv converter = new PeliculaSerieConv();
	
	@GetMapping(value = "/peliculasSeries")
	public ResponseEntity<WrapperResponse<List<PeliculaSerieDTO>>> findAll(){
		List<PeliculaSerie> peliSeri = impl.findAll();
		List<PeliculaSerieDTO> peliSeriDto = converter.toDTO(peliSeri);
		return new WrapperResponse<>(true, "Succes", peliSeriDto).createResponse(HttpStatus.OK);
	}
		
	@GetMapping(value = "/peliculasSeries/{idPeliculaSerie}")
	public ResponseEntity<WrapperResponse<PeliculaSerieDTO>> findById(@PathVariable("idPeliculaSerie") Long idPeliculaSerie){
		PeliculaSerie peliSeri = impl.findById(idPeliculaSerie);
		PeliculaSerieDTO peliSeriDto = converter.toDTO(peliSeri);
		return new WrapperResponse<>(true, "Success", peliSeriDto).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/peliculasSeries/{idPeliculaSerie}")
	public ResponseEntity<?> delete(@PathVariable("idPeliculaSerie")Long idPeliculaSerie) {
		impl.delete(idPeliculaSerie);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}
	
	@PostMapping(value = "/peliculasSeries")
	public ResponseEntity<WrapperResponse<PeliculaSerieDTO>> create(@RequestBody PeliculaSerieDTO peliculaSerie){
		PeliculaSerie peliSeriCreate = impl.create(converter.toEntity(peliculaSerie));
		PeliculaSerieDTO peliSeriCreateDto = converter.toDTO(peliSeriCreate);
		return new WrapperResponse<>(true, "Successfully created Movie/Serie", peliSeriCreateDto).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/peliculasSeries")
	public ResponseEntity<WrapperResponse<PeliculaSerieDTO>> update(@RequestBody PeliculaSerieDTO peliculaSerie){
		PeliculaSerie peliSeriUpdate = impl.update(converter.toEntity(peliculaSerie));
		PeliculaSerieDTO peliSeriUpDto = converter.toDTO(peliSeriUpdate);
		return new WrapperResponse<>(true, "Successfully updated Movie/Serie", peliSeriUpDto).createResponse(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
