package com.disney.alkemy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "relPersonajesPeliculas")
public class RelPersonajePelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRelPersonajePelicula")
	private Long idRelPersonajePelicula;

	@JoinColumn(name = "fkIdPersonaje" ,referencedColumnName = "idPersonaje")
	@ManyToOne(optional = false)
	private Personaje idPersonaje;

	@JoinColumn(name = "fkIdPeliculaSerie", referencedColumnName = "idPeliculaSerie")
	@ManyToOne(optional = false)
	private PeliculaSerie idPeliculaSerie;
}
