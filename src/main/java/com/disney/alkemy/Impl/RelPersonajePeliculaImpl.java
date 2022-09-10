package com.disney.alkemy.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.alkemy.daos.RelPersonajePeliculaDao;
import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.RelPersonajePelicula;
import com.disney.alkemy.validators.RelPersonajePeliculaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RelPersonajePeliculaImpl {

	@Autowired
	private RelPersonajePeliculaDao repository;

	@Transactional
	public void delete(Long idRel) {
		try {
			RelPersonajePelicula rel = repository.findById(idRel)
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
	public RelPersonajePelicula create(RelPersonajePelicula relPerPeli) {
		try {
			RelPersonajePeliculaValidator.validate(relPerPeli);
			RelPersonajePelicula relCreate = repository.save(relPerPeli);
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
	public RelPersonajePelicula update(RelPersonajePelicula relPerPeli) {
		try {
			RelPersonajePeliculaValidator.validate(relPerPeli);


			RelPersonajePelicula relUp = repository.findById(relPerPeli.getIdRelPersonajePelicula())
					.orElseThrow(()-> new NoDataFoundException("No exist the relation."));

			relUp.setIdPeliculaSerie(relPerPeli.getIdPeliculaSerie());
			relUp.setIdPersonaje(relPerPeli.getIdPersonaje());


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
