package com.disney.alkemy.Impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.alkemy.daos.PeliculaSerieDao;
import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.PeliculaSerie;
import com.disney.alkemy.validators.PeliculaSerieValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PeliculaSerieImpl {

	@Autowired
	private PeliculaSerieDao repository;


	public List<PeliculaSerie> findAll(Map<String, String> parameters) {
		try {
			if(parameters.isEmpty()) {
				List<PeliculaSerie> peliculas = repository.findAll();
				return peliculas;
			}
			for (Entry<String, String> param : parameters.entrySet()) {
				if(param.getKey().equals("name")) {
					return findPeliculaByTitulo(param.getValue());
				}
				if(param.getKey().equals("genre")) {
					return findPeliByIdGenero(Long.parseLong(param.getValue()));
				}
				if(param.getKey().equals("order")) {
					if(param.getValue().equals("ASC")) {
						return findAllAsc();
					}
					if(param.getValue().equals("DESC")) {
						return findAllDesc();
					}
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

	public List<PeliculaSerie> findPeliculaByTitulo(String titulo) {
		try {
			List<PeliculaSerie> peliSeri = repository.findByTitulo(titulo);
			return peliSeri;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<PeliculaSerie> findPeliByIdGenero(Long idPelicualSerie) {
		try {
			List<PeliculaSerie> peliSeri = repository.findByGeneros_IdGenero(idPelicualSerie);
			return peliSeri;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<PeliculaSerie> findAllAsc() {
		try {
			List<PeliculaSerie> peliSeri = repository.findAllByOrderByIdPeliculaSerieAsc();
			return peliSeri;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public List<PeliculaSerie> findAllDesc() {
		try {
			List<PeliculaSerie> peliSeri = repository.findAllByOrderByIdPeliculaSerieDesc();
			return peliSeri;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void delete(Long idPelicualSerie) {
		try {
			PeliculaSerie peliSeri = repository.findById(idPelicualSerie)
					.orElseThrow(() -> new NoDataFoundException("Character not exist."));
			repository.delete(peliSeri);
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public PeliculaSerie create(PeliculaSerie peliculaSerie) {
		try {
			PeliculaSerieValidator.validate(peliculaSerie);
			PeliculaSerie peliSeriCreate = repository.save(peliculaSerie);

			return peliSeriCreate;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public PeliculaSerie update(PeliculaSerie peliculaSerie) {
		try {
			PeliculaSerieValidator.validate(peliculaSerie);
			PeliculaSerie peliSeriUpdate = repository.findById(peliculaSerie.getIdPeliculaSerie())
					.orElseThrow(() -> new NoDataFoundException("Movie/Serie not exist."));

			peliSeriUpdate.setTitulo(peliculaSerie.getTitulo());
			peliSeriUpdate.setCalificacion(peliculaSerie.getCalificacion());
			peliSeriUpdate.setImagen(peliculaSerie.getImagen());
			peliSeriUpdate.setFechaCreacion(peliculaSerie.getFechaCreacion());

			repository.save(peliSeriUpdate);

			return peliSeriUpdate;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
