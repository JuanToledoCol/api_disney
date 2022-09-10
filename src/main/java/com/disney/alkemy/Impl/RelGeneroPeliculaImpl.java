package com.disney.alkemy.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.alkemy.daos.RelGeneroPeliculaDao;
import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.RelGeneroPelicula;
import com.disney.alkemy.validators.RelGeneroPeliculaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RelGeneroPeliculaImpl {

	@Autowired
	private RelGeneroPeliculaDao repository;

	@Transactional
	public void delete(Long idRel) {
		try {
			RelGeneroPelicula rel = repository.findById(idRel)
					.orElseThrow(() -> new NoDataFoundException("No exist the relation."));
			repository.delete(rel);
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public RelGeneroPelicula create(RelGeneroPelicula relGenPeli) {
		try {
			RelGeneroPeliculaValidator.validate(relGenPeli);
			RelGeneroPelicula relCreate = repository.save(relGenPeli);
			return relCreate;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public RelGeneroPelicula update(RelGeneroPelicula relPerPeli) {
		try {
			RelGeneroPeliculaValidator.validate(relPerPeli);


			RelGeneroPelicula relUp = repository.findById(relPerPeli.getIdRelGeneroPelicula())
					.orElseThrow(()-> new NoDataFoundException("No exist the relation."));

			relUp.setIdPeliculaSerie(relPerPeli.getIdPeliculaSerie());
			relUp.setIdGenero(relPerPeli.getIdGenero());


			repository.save(relUp);

			return relUp;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
