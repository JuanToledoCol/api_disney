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

import com.disney.alkemy.Impl.RelPersonajePeliculaImpl;
import com.disney.alkemy.converters.RelPersonajePeliculaConv;
import com.disney.alkemy.dtos.RelPersonajePeliculaDTO;
import com.disney.alkemy.models.RelPersonajePelicula;
import com.disney.alkemy.utils.WrapperResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/add-character-movie")
public class RelPersonajePeliculaController {

	@Autowired
	private RelPersonajePeliculaImpl impl;

	private RelPersonajePeliculaConv converter = new RelPersonajePeliculaConv();

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Borrar un personaje de una pelicula.", description = "<p>Para borrar un personaje de una pelicula debe pasar el idRelPersonajePelicula que contiene la relacion que quiere eliminar, como parametro.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete character of movie "),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	// Mapping
	@DeleteMapping(value = "/{idRelPerPel}")
	public ResponseEntity<?> delete(@PathVariable("idRelPerPel") Long idRelPerPel) {
		impl.delete(idRelPerPel);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Relacionar un personaje con una pelicula.", description = "<p>Para crear la relacion solo deberá pasar el ID del personaje y el ID de la pelicula. No debe incluir el idRelPersonajePelicula ni el cuerpo de las otras entidades.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = RelPersonajePelicula.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Create relation character on movie.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RelPersonajePelicula.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Not found") })
	// Mapping
	@PostMapping(value = "/")
	public ResponseEntity<WrapperResponse<RelPersonajePeliculaDTO>> create(
			@RequestBody RelPersonajePeliculaDTO relPerPEl) {
		RelPersonajePelicula relPerPelCreate = impl.create(converter.toEntity(relPerPEl));
		RelPersonajePeliculaDTO relCreateDto = converter.toDTO(relPerPelCreate);
		return new WrapperResponse<>(true, "Successfully created relation Characters-Movie/Serie", relCreateDto)
				.createResponse(HttpStatus.CREATED);
	}

	// Documentation
		@Operation(security = {
				@SecurityRequirement(name = "Bearer") }, summary = "Actualizar la relacion de un personaje con una pelicula.", description = "<p>Para actualizar la relacion solo deberá pasar los ID de las entidades, no debe incluir el resto del cuerpo de cada entidad.")
		@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = RelPersonajePelicula.class)))
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Create relation character on movie.", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = RelPersonajePelicula.class)) }),
				@ApiResponse(responseCode = "500", description = "Internal Server Error"),
				@ApiResponse(responseCode = "400", description = "Invalid request"),
				@ApiResponse(responseCode = "404", description = "Not found") })
		// Mapping
	@PutMapping(value = "/")
	public ResponseEntity<WrapperResponse<RelPersonajePeliculaDTO>> update(
			@RequestBody RelPersonajePeliculaDTO genero) {
		RelPersonajePelicula relPerPelUpdate = impl.update(converter.toEntity(genero));
		RelPersonajePeliculaDTO relUpDto = converter.toDTO(relPerPelUpdate);
		return new WrapperResponse<>(true, "Successfully updated relation Characters-Movie/Serie", relUpDto)
				.createResponse(HttpStatus.OK);
	}
}
