package com.disney.alkemy.daos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.RelPersonajePelicula;

@Repository
public interface RelPersonajePeliculaDao extends JpaRepository<RelPersonajePelicula, Long>{

}
