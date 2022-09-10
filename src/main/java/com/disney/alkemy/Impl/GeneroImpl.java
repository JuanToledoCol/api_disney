package com.disney.alkemy.Impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.alkemy.daos.GeneroDao;
import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.Genero;
import com.disney.alkemy.validators.GeneroValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GeneroImpl {

	@Autowired
	private GeneroDao repository;

	public List<Genero> findAll(Map<String, String> parameters) {
		try {
			if(parameters.isEmpty()) {
				List<Genero> genero = repository.findAll();
				return genero;
			}
			for (Entry<String, String> param: parameters.entrySet()) {
				if(param.getKey().equals("name")) {
					return findByNombreGenero(param.getValue());
				}
				if(param.getKey().equals("peliculas")) {
					return findGeneroByIdPelicula(Long.parseLong(param.getValue()));
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

	public List<Genero> findByNombreGenero(String nombreGenero) {
		try {
			List<Genero> peliculas = repository.findByNombreGenero(nombreGenero);
			return peliculas;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<Genero> findGeneroByIdPelicula(Long idGenero) {
		try {
			List<Genero> genero = repository.findByPeliculasSeries_IdPeliculaSerie(idGenero);
			return genero;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void delete(Long idGenero) {
		try {
			Genero genero = repository.findById(idGenero)
					.orElseThrow(() -> new NoDataFoundException("Genero not exist."));
			repository.delete(genero);
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public Genero create(Genero Genero) {
		try {
			GeneroValidator.validate(Genero);
			Genero generoCreate = repository.save(Genero);

			return generoCreate;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public Genero update(Genero genero) {
		try {
			GeneroValidator.validate(genero);
			Genero generoUp = repository.findById(genero.getIdGenero())
					.orElseThrow(() -> new NoDataFoundException("Movie/Serie not exist."));

			generoUp.setNombreGenero(genero.getNombreGenero());
			generoUp.setImagen(genero.getImagen());

			repository.save(generoUp);

			return generoUp;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
