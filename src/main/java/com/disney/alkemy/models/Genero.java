package com.disney.alkemy.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "generos")
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGenero", nullable = false)
	private Long idGenero;

	@Column(name = "nombreGenero")
	private String nombreGenero;

	@Column(name = "imagen")
	private String imagen;

	@JsonIgnore
	@ManyToMany(mappedBy = "generos")
	private List<PeliculaSerie> peliculasSeries;
}
