package com.disney.alkemy.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disney.alkemy.converters.UsuarioConv;
import com.disney.alkemy.daos.UsuarioDao;
import com.disney.alkemy.dtos.LoginResponseDTO;
import com.disney.alkemy.dtos.UsuarioDTO;
import com.disney.alkemy.exceptions.GeneralServiceException;
import com.disney.alkemy.exceptions.NoDataFoundException;
import com.disney.alkemy.exceptions.ValidateServiceException;
import com.disney.alkemy.models.Usuario;
import com.disney.alkemy.validators.UsuarioValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioImpl {
	
	@Value("${jwt.clave}")
	private String keySecret;
	
	@Autowired
	private UsuarioDao repository;
	
	private UsuarioConv converter = new UsuarioConv();
	
	@Transactional
	public Usuario signUp(Usuario usuario) {
		try {
			Usuario buscarU = repository.findByUsuario(usuario.getUsuario()).orElse(null);
			if (buscarU != null) {
				throw new ValidateServiceException("The username is used.");
			}
			UsuarioValidator.validate(usuario);
			Usuario usuarioN = repository.save(EncoderPass(usuario));
			return usuarioN;
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public LoginResponseDTO login(UsuarioDTO request) {
		try {
			Usuario usuario = repository.findByUsuario(request.getUsuario())
					.orElseThrow(() -> new ValidateServiceException("User or Password invalid."));

			if (!validatePass(usuario, request.getClave())) {
				throw new ValidateServiceException("El nombre o contraseña es invalido");
			}

			String token = generateToken(usuario);
			return new LoginResponseDTO(converter.toDTO(usuario), token);

		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public String generateToken(Usuario usuario) {
		Date now = new Date();
		Date expireDate = new Date(now.getTime() + (1000 * 60 * 60));

		return Jwts.builder()
				.setSubject(usuario.getUsuario())
				.setIssuedAt(now)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, keySecret)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(keySecret).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			log.error("JWT in a particular format/configuration that does not match the format expected");
		} catch (MalformedJwtException e) {
			log.error("JWT was not correctly constructed and should be rejected.");
		} catch (SignatureException e) {
			log.error("signature or verifying an existing signature of a JWT failed.");
		}catch (ExpiredJwtException e) {
			log.error("JWT was accepted after it expired and must be rejected.");
		}
		return false;
	}

	public String getUsuarioFromJwt(String jwt) {
		try {
			return Jwts.parser().setSigningKey(keySecret).parseClaimsJws(jwt).getBody().getSubject();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ValidateServiceException("El token es inavalido");
		}
	}

	private Usuario EncoderPass(Usuario usuario) {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(16);
		usuario.setClave(passEncoder.encode(usuario.getClave())) ;
		return usuario;
	}

	private boolean validatePass(Usuario usuario, String request) {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return passEncoder.matches(request, usuario.getClave());
	}
	
}
