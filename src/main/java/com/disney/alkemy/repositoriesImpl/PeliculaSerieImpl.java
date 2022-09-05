package com.disney.alkemy.repositoriesImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.PeliculaSerie;
import com.disney.alkemy.repositories.PeliculaSerieRepository;
import com.disney.alkemy.validators.PeliculaSerieValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PeliculaSerieImpl {

	@Autowired
	private PeliculaSerieRepository repository;
	
	public List<PeliculaSerie> findAll() {
		try {
			List<PeliculaSerie> peliSeri = repository.findAll();
			return peliSeri;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public PeliculaSerie findById(Long idPelicualSerie) {
		try {
			PeliculaSerie peliSeri = repository.findById(idPelicualSerie)
					.orElseThrow(() -> new NoDataFoundException("Movie/serie not exist."));
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
