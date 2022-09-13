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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.disney.alkemy.Impl.PeliculaSerieImpl;
import com.disney.alkemy.converters.PeliculaSerieConv;
import com.disney.alkemy.dtos.PeliculaSerieDTO;
import com.disney.alkemy.models.PeliculaSerie;
import com.disney.alkemy.utils.WrapperResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/movies")
public class PeliculaSerieController {

	@Autowired
	private PeliculaSerieImpl impl;

	private PeliculaSerieConv converter = new PeliculaSerieConv();

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Mostrar todas las peliculas con sus detalles.", description = "<p>Puede filtrar por 1 de los siguientes parametros: 'name', 'genre', 'order'."
					+ "</br><b>name:</b> Nombre de la pelicula." + "</br><b>genre:</b> Genero."
					+ "</br><b>order:</b> Puede ordenar con los siguientes valores: ASC | DESC</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@Parameters(value = { @Parameter(name = "name", description = "Nombre de la pelicula.", in = ParameterIn.QUERY),
			@Parameter(name = "genre", description = "Genero de la pelicula.", in = ParameterIn.QUERY),
			@Parameter(name = "order", description = "Orden", in = ParameterIn.QUERY),
			@Parameter(name = "parameters", hidden = true) })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found movie/S", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PeliculaSerieDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Movie not found") })
	// Mapping
	@GetMapping(value = "/")
	public ResponseEntity<WrapperResponse<List<PeliculaSerieDTO>>> findAll(
			@RequestParam Map<String, String> parameters) {
		List<PeliculaSerie> peliSeri = impl.findAll(parameters);
		List<PeliculaSerieDTO> peliSeriDto = converter.toDTO(peliSeri);
		return new WrapperResponse<>(true, "Succes", peliSeriDto).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Borrar una pelicula.", description = "<p>Para borrar una pelicula debe pasar el ID como parametro."
					+ "Adicionalmente usted debe asegurarse de que la pelicula no este asociado a ninguna relacion con algun Genero o Personaje.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete movie"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Movie not found") })
	// Mapping
	@DeleteMapping(value = "/{idPeliculaSerie}")
	public ResponseEntity<?> delete(@PathVariable("idPeliculaSerie") Long idPeliculaSerie) {
		impl.delete(idPeliculaSerie);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Crear una pelicula.", description = "<p>Para Crear una pelicula debe pasar la entidad completa sin el idPeliculaSerie</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = PeliculaSerie.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created movie/S", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PeliculaSerie.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Movie not found") })
	// Mapping
	@PostMapping(value = "/")
	public ResponseEntity<WrapperResponse<PeliculaSerieDTO>> create(@RequestBody PeliculaSerieDTO peliculaSerie) {
		PeliculaSerie peliSeriCreate = impl.create(converter.toEntity(peliculaSerie));
		PeliculaSerieDTO peliSeriCreateDto = converter.toDTO(peliSeriCreate);
		return new WrapperResponse<>(true, "Successfully created movies/Serie", peliSeriCreateDto)
				.createResponse(HttpStatus.CREATED);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Actualizar una pelicula.", description = "<p>Para actualizar una pelicula debe pasar la entidad completa.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = PeliculaSerie.class)))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Created movie.", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PeliculaSerieDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Movie not found") })
	// Mapping
	@PutMapping(value = "/")
	public ResponseEntity<WrapperResponse<PeliculaSerieDTO>> update(@RequestBody PeliculaSerieDTO peliculaSerie) {
		PeliculaSerie peliSeriUpdate = impl.update(converter.toEntity(peliculaSerie));
		PeliculaSerieDTO peliSeriUpDto = converter.toDTO(peliSeriUpdate);
		return new WrapperResponse<>(true, "Successfully updated movies/Serie", peliSeriUpDto)
				.createResponse(HttpStatus.OK);
	}

}
