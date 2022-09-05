package com.disney.alkemy.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.alkemy.models.RelPersonajePelicula;

@Repository
public interface RelPersonajePeliculaRepository extends JpaRepository<RelPersonajePelicula, Long>{

}
