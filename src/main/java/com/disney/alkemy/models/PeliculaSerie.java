package com.disney.alkemy.models;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy = "peliculasSeries")
	private List<Personaje> personajes;
}
