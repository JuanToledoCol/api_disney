package com.disney.alkemy.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbsConverter<E, D> {

	public abstract E toEntity(D dto);

	public abstract D toDTO(E entity);

	public List<E> toEntity(List<D> dtos){
		return dtos.stream().map(e -> toEntity(e)).collect(Collectors.toList());
	}

	public List<D> toDTO(List<E> entitys){
		return entitys.stream().map(e -> toDTO(e)).collect(Collectors.toList());
	}
}
