package com.disney.alkemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disney.alkemy.Impl.RelGeneroPeliculaImpl;
import com.disney.alkemy.converters.RelGeneroPeliculaConv;
import com.disney.alkemy.dtos.RelGeneroPeliculaDTO;
import com.disney.alkemy.models.RelGeneroPelicula;
import com.disney.alkemy.utils.WrapperResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/add-gender-movie")
public class RelGeneroPeliculaController {

	@Autowired
	private RelGeneroPeliculaImpl impl;

	private RelGeneroPeliculaConv converter = new RelGeneroPeliculaConv();

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Borrar una pelicula de un genero", description = "<p>Para borrar una pelicula de un genero debe pasar el idRelGeneroPelicula que contiene la relacion que quiere eliminar, como parametro.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete movie of gender"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	// Mapping
	@DeleteMapping(value = "/{idGenPerPel}")
	public ResponseEntity<?> delete(@PathVariable("idGenPerPel") Long idGenPerPel) {
		impl.delete(idGenPerPel);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Relacionar una pelicula con un genero", description = "<p>Para crear la relacion solo deberá pasar el ID del genero y el ID de la pelicula. No debe incluir el idRelGeneroPelicula ni el cuerpo de las otras entidades.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = RelGeneroPelicula.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Create relation movie on gender", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RelGeneroPelicula.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	// Mapping
	@PostMapping(value = "/")
	public ResponseEntity<WrapperResponse<RelGeneroPeliculaDTO>> create(@RequestBody RelGeneroPeliculaDTO relGenPel) {
		RelGeneroPelicula relGenPelCreate = impl.create(converter.toEntity(relGenPel));
		RelGeneroPeliculaDTO relCreateDto = converter.toDTO(relGenPelCreate);
		return new WrapperResponse<>(true, "Successfully created relation Geneder Movie/Serie", relCreateDto)
				.createResponse(HttpStatus.CREATED);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Actualizar la relacion de una pelicula con un genero", description = "<p>Para actualizar la relacion solo deberá pasar los ID de las entidades, no debe incluir el resto del cuerpo de cada entidad.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = RelGeneroPelicula.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Create relation movie on gender", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RelGeneroPelicula.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	// Mapping
	@PutMapping(value = "/")
	public ResponseEntity<WrapperResponse<RelGeneroPeliculaDTO>> update(@RequestBody RelGeneroPeliculaDTO relGenPel) {
		RelGeneroPelicula relGenPelUpdate = impl.update(converter.toEntity(relGenPel));
		RelGeneroPeliculaDTO relUpDto = converter.toDTO(relGenPelUpdate);
		return new WrapperResponse<>(true, "Successfully updated relation Gender Movie/Serie", relUpDto)
				.createResponse(HttpStatus.OK);
	}
}
