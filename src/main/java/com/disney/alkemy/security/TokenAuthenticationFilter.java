package com.disney.alkemy.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.disney.alkemy.Impl.UsuarioImpl;
import com.disney.alkemy.daos.UsuarioDao;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.models.Usuario;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UsuarioDao repository;

	@Autowired
	private UsuarioImpl impl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && impl.validateToken(jwt)) {
				String usuario = impl.getUsuarioFromJwt(jwt);

				Usuario usuarioValidate = repository.findByUsuario(usuario)
						.orElseThrow(() -> new NoDataFoundException("El usuario no existe"));

				UsuarioAuthentication userAuthentication = UsuarioAuthentication.create(usuarioValidate);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userAuthentication, null, userAuthentication.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("Error al autenticar el usuario", e);
		}

		filterChain.doFilter(request, response);
	}

	public String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
