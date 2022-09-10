package com.disney.alkemy.models;


import java.time.LocalDate;
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
@Table(name = "peliculasSeries")
public class PeliculaSerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPeliculaSerie")
	private Long idPeliculaSerie;

	@Column(name = "titulo", nullable = false, length = 45)
	private String titulo;

	@Column(name = "fechaCreacion", nullable = false)
	private LocalDate fechaCreacion;

	@Column(name = "calificacion", nullable = false, length = 5)
	private int calificacion;

	@Column(name = "imagen", nullable = false)
	private String imagen;

	@JsonIgnore
	@ManyToMany(mappedBy = "peliculasSeries")
	private List<Personaje> personajes;

	@JsonIgnore
	@JoinTable(name = "relGenerosPeliculas",
				joinColumns = @JoinColumn(name = "fkIdPeliculaSerie", nullable = false),
				inverseJoinColumns = @JoinColumn(name ="fkIdGenero", nullable = false)
	)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Genero> generos;
}
