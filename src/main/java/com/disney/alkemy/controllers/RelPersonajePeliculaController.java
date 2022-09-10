package com.disney.alkemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.disney.alkemy.Impl.RelPersonajePeliculaImpl;
import com.disney.alkemy.converters.RelPersonajePeliculaConv;
import com.disney.alkemy.dtos.RelPersonajePeliculaDTO;
import com.disney.alkemy.models.RelPersonajePelicula;
import com.disney.alkemy.utils.WrapperResponse;

@RestController
public class RelPersonajePeliculaController {

	@Autowired
	private RelPersonajePeliculaImpl impl;

	private RelPersonajePeliculaConv converter = new RelPersonajePeliculaConv();


	@DeleteMapping(value = "/relPerPel/{idRelPerPel}")
	public ResponseEntity<?> delete(@PathVariable("idRelPerPel")Long idRelPerPel) {
		impl.delete(idRelPerPel);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/relPerPel")
	public ResponseEntity<WrapperResponse<RelPersonajePeliculaDTO>> create(@RequestBody RelPersonajePeliculaDTO relPerPEl){
		RelPersonajePelicula relPerPelCreate = impl.create(converter.toEntity(relPerPEl));
		RelPersonajePeliculaDTO relCreateDto = converter.toDTO(relPerPelCreate);
		return new WrapperResponse<>(true, "Successfully created relation Characters-Movie/Serie", relCreateDto).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/relPerPel")
	public ResponseEntity<WrapperResponse<RelPersonajePeliculaDTO>> update(@RequestBody RelPersonajePeliculaDTO genero){
		RelPersonajePelicula relPerPelUpdate = impl.update(converter.toEntity(genero));
		RelPersonajePeliculaDTO relUpDto = converter.toDTO(relPerPelUpdate);
		return new WrapperResponse<>(true, "Successfully updated relation Characters-Movie/Serie", relUpDto).createResponse(HttpStatus.OK);
	}
}
