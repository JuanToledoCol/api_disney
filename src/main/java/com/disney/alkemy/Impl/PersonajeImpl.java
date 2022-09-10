package com.disney.alkemy.Impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.alkemy.daos.PersonajeDao;
import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.Personaje;
import com.disney.alkemy.validators.PersonajeValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonajeImpl {

	@Autowired
	private PersonajeDao repository;

	public List<Personaje> findAll(Map<String, String> parameters) {
		try {
			if(parameters.isEmpty()) {
				List<Personaje> personajes = repository.findAll();
				return personajes;
			}
			for (Entry<String, String> param: parameters.entrySet()) {
				if(param.getKey().equals("name")) {
					return findByNombrePersonaje(param.getValue());
				}
				if(param.getKey().equals("age")) {
					return findByEdad(Integer.parseInt(param.getValue()));
				}
				if(param.getKey().equals("peso")) {
					return findByPeso(Double.parseDouble(param.getValue()));
				}
				if(param.getKey().equals("movie")) {
					return findByIdSerie(Long.parseLong(param.getValue()));
				}

			}
			return null;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<Personaje> findByNombrePersonaje(String nombrePersonaje){
		try {
			List<Personaje> personajePeliculas = repository.findByNombrePersonaje(nombrePersonaje);
			return personajePeliculas;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<Personaje> findByEdad(int edad) {
		try {
			List<Personaje> personaje = repository.findByEdad(edad);
			return personaje;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<Personaje> findByPeso(double peso){
		try {
			List<Personaje> personajePeliculas = repository.findByPeso(peso);
			return personajePeliculas;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<Personaje> findByIdSerie(Long idPelicula){
		try {
			List<Personaje> personajePeliculas = repository.findByPeliculasSeries_IdPeliculaSerie(idPelicula);
			return personajePeliculas;
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
