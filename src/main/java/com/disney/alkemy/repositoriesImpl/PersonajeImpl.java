package com.disney.alkemy.repositoriesImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.PeliculaSerie;
import com.disney.alkemy.models.Personaje;
import com.disney.alkemy.repositories.PeliculaSerieRepository;
import com.disney.alkemy.repositories.PersonajeRepository;
import com.disney.alkemy.validators.PersonajeValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonajeImpl {

	@Autowired
	private PersonajeRepository repository;
	
	@Autowired
	private PeliculaSerieRepository repositoryPeli;

	public List<Personaje> findAll() {
		try {
			List<Personaje> personajes = repository.findAll();
			return personajes;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public Personaje findById(Long idPersonaje) {
		try {
			Personaje personaje = repository.findById(idPersonaje)
					.orElseThrow(() -> new NoDataFoundException("Character not exist."));
			return personaje;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public List<Personaje> findByTituloSerie(String titulo){
		try {
			List<Personaje> personajePeliculas = repository.findByPeliculasSeries_Titulo(titulo);
			return personajePeliculas;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public List<PeliculaSerie> findSerieByPersonaje(String personaje){
		try {
			List<PeliculaSerie> seriePersonaje = repositoryPeli.findByPersonajes_NombrePersonaje(personaje);
			return seriePersonaje;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public void delete(Long idPersonaje) {
		try {
			Personaje personaje = repository.findById(idPersonaje)
					.orElseThrow(() -> new NoDataFoundException("Character not exist."));
			repository.delete(personaje);
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public Personaje create(Personaje personaje) {
		try {
			PersonajeValidator.validate(personaje);
			Personaje personajeCreate = repository.save(personaje);
			return personajeCreate;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public Personaje update(Personaje personaje) {
		try {
			PersonajeValidator.validate(personaje);
			Personaje personajeUpdate = repository.findById(personaje.getIdPersonaje())
					.orElseThrow(() -> new NoDataFoundException("Character not exist."));
			
			personajeUpdate.setNombrePersonaje(personaje.getNombrePersonaje());
			personajeUpdate.setHistoria(personaje.getHistoria());
			personajeUpdate.setImagen(personaje.getImagen());
			personajeUpdate.setEdad(personaje.getEdad());
			personajeUpdate.setPeso(personaje.getPeso());
			repository.save(personajeUpdate);
			
			return personajeUpdate;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	
}
