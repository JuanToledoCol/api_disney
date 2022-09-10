package com.disney.alkemy.daos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.RelGeneroPelicula;

@Repository
public interface RelGeneroPeliculaDao extends JpaRepository<RelGeneroPelicula, Long>{

}
