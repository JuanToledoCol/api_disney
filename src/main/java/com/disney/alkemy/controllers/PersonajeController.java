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

import com.disney.alkemy.Impl.PersonajeImpl;
import com.disney.alkemy.converters.PersonajeConv;
import com.disney.alkemy.dtos.PersonajeDTO;
import com.disney.alkemy.models.Personaje;
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
@RequestMapping("/api/characters")
public class PersonajeController {

	@Autowired
	private PersonajeImpl impl;

	private PersonajeConv converter = new PersonajeConv();

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Mostrar todos los personajes con sus detalles.", description = "<p>Puede filtrar por 1 de los siguientes parametros: 'name', 'age', 'peso', 'movie'."
					+ "</br><b>name:</b> nombre del personaje." + "</br><b>age:</b> edad del personaje."
					+ "</br><b>peso:</b> peso del personaje."
					+ "</br><b>movie:</b> ID de la pelicula, esto retorna todos los personajes que pertenecen a esa pelicula.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@Parameters(value = { @Parameter(name = "name", description = "Nombre del personaje.", in = ParameterIn.QUERY),
			@Parameter(name = "age", description = "Edad del personaje.", in = ParameterIn.QUERY),
			@Parameter(name = "peso", description = "Peso del personaje.", in = ParameterIn.QUERY),
			@Parameter(name = "movie", description = "Id de la pelicula, traera los personajes que aparecen en ella.", in = ParameterIn.QUERY),
			@Parameter(name = "parameters", hidden = true) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found character/S", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonajeDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Character not found") })
	// Mapping
	@GetMapping(value = "/")
	public ResponseEntity<WrapperResponse<List<PersonajeDTO>>> findAll(@RequestParam Map<String, String> parameters) {
		List<Personaje> personajes = impl.findAll(parameters);
		List<PersonajeDTO> personajesDto = converter.toDTO(personajes);
		return new WrapperResponse<>(true, "Success", personajesDto).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Borrar un personaje.", description = "<p>Para borrar un personaje debe pasar el ID como parametro."
					+ "Adicionalmente usted debe asegurarse de que el personaje no este asociado a ninguna relacion con alguna pelicula.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete character/S"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Character not found") })
	// Mapping
	@DeleteMapping(value = "/{idPersonaje}")
	public ResponseEntity<?> delete(@PathVariable("idPersonaje") Long idPersonaje) {
		impl.delete(idPersonaje);
		return new WrapperResponse<>(true, "Delete Success", null).createResponse(HttpStatus.OK);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Crear un personaje.", description = "<p>Para Crear un personaje debe pasar la entidad completa sin el idPersonaje</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Personaje.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created character/S", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Personaje.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Character not found") })
	// Mapping
	@PostMapping(value = "/")
	public ResponseEntity<WrapperResponse<PersonajeDTO>> create(@RequestBody PersonajeDTO personajeDto) {
		Personaje personajeCreate = impl.create(converter.toEntity(personajeDto));
		PersonajeDTO personajeCreateDto = converter.toDTO(personajeCreate);
		return new WrapperResponse<>(true, "Successfully created character", personajeCreateDto)
				.createResponse(HttpStatus.CREATED);
	}

	// Documentation
	@Operation(security = {
			@SecurityRequirement(name = "Bearer") }, summary = "Actualizar un personaje.", description = "<p>Para actualizar un personaje debe pasar la entidad completa.</p>"
					+ "</br><p>Para acceder al endpoint debe pasar un token de seguridad JWT puede hacer el proceso desde los siguientes endpoints: "
					+ "</br><b>SignUp:</b> /api/auth/signup " + "</br><b>Login:</b> /api/auth/login</p>")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Personaje.class)))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated character/S", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonajeDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "404", description = "Character not found") })
	// Mapping
	@PutMapping(value = "/")
	public ResponseEntity<WrapperResponse<PersonajeDTO>> update(@RequestBody PersonajeDTO personajeDto) {
		Personaje personajeUp = impl.update(converter.toEntity(personajeDto));
		PersonajeDTO personajeUpDto = converter.toDTO(personajeUp);
		return new WrapperResponse<>(true, "Successfully updated character", personajeUpDto)
				.createResponse(HttpStatus.OK);
	}
}
