package com.disney.alkemy.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "personajes")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPersonaje", nullable = false)
	private Long idPersonaje;

	@Column(name = "nombrePersonaje", nullable = false, length = 50)
	private String nombrePersonaje;

	@Column(name = "imagen", nullable = true, length = 50)
	private String imagen;

	@Column(name = "edad", nullable = false)
	private int edad;

	@Column(name = "peso", nullable = false)
	private double peso;

	@Column(name = "historia", nullable = false, length = 50)
	private String historia;

	@JsonIgnore
	@JoinTable(
			name = "relPersonajesPeliculas",
			joinColumns = @JoinColumn(name = "fkIdPersonaje", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "fkIdPeliculaSerie", nullable = false)
			)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PeliculaSerie> peliculasSeries;
}
