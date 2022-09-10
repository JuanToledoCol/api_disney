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

import com.disney.alkemy.Impl.GeneroImpl;
import com.disney.alkemy.converters.GeneroConv;
import com.disney.alkemy.dtos.GeneroDTO;
import com.disney.alkemy.models.Genero;
import com.disney.alkemy.utils.WrapperResponse;

@RestController
public class GeneroController {

	@Autowired
	private GeneroImpl impl;

	private GeneroConv converter = new GeneroConv();

	@GetMapping(value = "/gender")
	public ResponseEntity<WrapperResponse<List<GeneroDTO>>> findAll(@RequestParam Map<String, String> parameters){
		List<Genero> genero = impl.findAll(parameters);
		List<GeneroDTO> generoDto = converter.toDTO(genero);
		return new WrapperResponse<>(true, "Succes", generoDto).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/gender/{idGenero}")
	public ResponseEntity<?> delete(@PathVariable("idGenero")Long idGenero) {
		impl.delete(idGenero);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}

	@PostMapping(value = "/gender")
	public ResponseEntity<WrapperResponse<GeneroDTO>> create(@RequestBody GeneroDTO genero){
		Genero generoCreate = impl.create(converter.toEntity(genero));
		GeneroDTO generoCreateDto = converter.toDTO(generoCreate);
		return new WrapperResponse<>(true, "Successfully created Gender", generoCreateDto).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = "/gender")
	public ResponseEntity<WrapperResponse<GeneroDTO>> update(@RequestBody GeneroDTO genero){
		Genero generoUpdate = impl.update(converter.toEntity(genero));
		GeneroDTO generoUpDto = converter.toDTO(generoUpdate);
		return new WrapperResponse<>(true, "Successfully updated Gender", generoUpDto).createResponse(HttpStatus.OK);
	}
}
