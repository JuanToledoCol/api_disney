package com.disney.alkemy.converters;

import com.disney.alkemy.dtos.UsuarioDTO;
import com.disney.alkemy.models.Usuario;

public class UsuarioConv extends AbsConverter<Usuario, UsuarioDTO>{

	@Override
	public Usuario toEntity(UsuarioDTO dto) {
		return Usuario.builder()
		.usuario(dto.getUsuario())
		.clave(dto.getClave())
		.build();
	}

	@Override
	public UsuarioDTO toDTO(Usuario entity) {
		return UsuarioDTO.builder()
		.usuario(entity.getUsuario())
		.clave(entity.getClave())
		.build();
	}

}
