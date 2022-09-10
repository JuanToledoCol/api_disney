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

import com.disney.alkemy.Impl.RelGeneroPeliculaImpl;
import com.disney.alkemy.converters.RelGeneroPeliculaConv;
import com.disney.alkemy.dtos.RelGeneroPeliculaDTO;
import com.disney.alkemy.models.RelGeneroPelicula;
import com.disney.alkemy.utils.WrapperResponse;

@RestController
public class RelGeneroPeliculaController {

	@Autowired
	private RelGeneroPeliculaImpl impl;

	private RelGeneroPeliculaConv converter = new RelGeneroPeliculaConv();


	@DeleteMapping(value = "/relGenPel/{idGenPerPel}")
	public ResponseEntity<?> delete(@PathVariable("idGenPerPel")Long idGenPerPel) {
		impl.delete(idGenPerPel);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/relGenPel")
	public ResponseEntity<WrapperResponse<RelGeneroPeliculaDTO>> create(@RequestBody RelGeneroPeliculaDTO relGenPel){
		RelGeneroPelicula relGenPelCreate = impl.create(converter.toEntity(relGenPel));
		RelGeneroPeliculaDTO relCreateDto = converter.toDTO(relGenPelCreate);
		return new WrapperResponse<>(true, "Successfully created relation Geneder Movie/Serie", relCreateDto).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/relGenPel")
	public ResponseEntity<WrapperResponse<RelGeneroPeliculaDTO>> update(@RequestBody RelGeneroPeliculaDTO relGenPel){
		RelGeneroPelicula relGenPelUpdate = impl.update(converter.toEntity(relGenPel));
		RelGeneroPeliculaDTO relUpDto = converter.toDTO(relGenPelUpdate);
		return new WrapperResponse<>(true, "Successfully updated relation Gender Movie/Serie", relUpDto).createResponse(HttpStatus.OK);
	}
}
