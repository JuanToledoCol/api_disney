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

import com.disney.alkemy.Impl.GeneroImpl;
import com.disney.alkemy.converters.GeneroConv;
import com.disney.alkemy.dtos.GeneroDTO;
import com.disney.alkemy.models.Genero;
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
@RequestMapping("/api/genders")
public class GeneroController {

	@Autowired
	private GeneroImpl impl;

	private GeneroConv converter = new GeneroConv();

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Mostrar todas los generos con sus detalles.", description = "<p>Puede filtrar por 1 de los siguientes parametros: 'name', 'peliculas'."
					+ "</br><b>name:</b> Nombre del genero."
					+ "</br><b>pelicula:</b> ID de una pelicula. Esto traera la entidad completa.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@Parameters(value = { @Parameter(name = "name", description = "Nombre del genero.", in = ParameterIn.QUERY),
			@Parameter(name = "pelicula", description = "Id de la pelicula.", in = ParameterIn.QUERY),
			@Parameter(name = "parameters", hidden = true) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found gender/S", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GeneroDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Gender not found") })
	// Mapping
	@GetMapping(value = "/")
	public ResponseEntity<WrapperResponse<List<GeneroDTO>>> findAll(@RequestParam Map<String, String> parameters) {
		List<Genero> genero = impl.findAll(parameters);
		List<GeneroDTO> generoDto = converter.toDTO(genero);
		return new WrapperResponse<>(true, "Succes", generoDto).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Borrar un genero.", description = "<p>Para borrar un genero debe pasar el ID como parametro."
					+ "Adicionalmente usted debe asegurarse de que el genero no este asociado a ninguna relacion con alguna pelicula.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete gender"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Gender not found") })
	// Mapping
	@DeleteMapping(value = "/{idGenero}")
	public ResponseEntity<?> delete(@PathVariable("idGenero") Long idGenero) {
		impl.delete(idGenero);
		return new WrapperResponse<>(true, "Delete success", null).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Crear un genero.", description = "<p>Para Crear un genero debe pasar la entidad completa sin el idGenero</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Genero.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created gender", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Genero.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Gender not found") })
	// Mapping
	@PostMapping(value = "/")
	public ResponseEntity<WrapperResponse<GeneroDTO>> create(@RequestBody GeneroDTO genero) {
		Genero generoCreate = impl.create(converter.toEntity(genero));
		GeneroDTO generoCreateDto = converter.toDTO(generoCreate);
		return new WrapperResponse<>(true, "Successfully created Gender", generoCreateDto)
				.createResponse(HttpStatus.CREATED);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Actualizar un genero.", description = "<p>Para actualizar un genero debe pasar la entidad completa.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Genero.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created gender.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GeneroDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Gender not found") })
	// Mapping
	@PutMapping(value = "/")
	public ResponseEntity<WrapperResponse<GeneroDTO>> update(@RequestBody GeneroDTO genero) {
		Genero generoUpdate = impl.update(converter.toEntity(genero));
		GeneroDTO generoUpDto = converter.toDTO(generoUpdate);
		return new WrapperResponse<>(true, "Successfully updated Gender", generoUpDto).createResponse(HttpStatus.OK);
	}
}
