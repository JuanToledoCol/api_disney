package com.disney.alkemy.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.disney.alkemy.security.RestAuthenticationEntryPoint;
import com.disney.alkemy.security.TokenAuthenticationFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public com.disney.alkemy.security.TokenAuthenticationFilter createTokenAuthFilter() {
		return new TokenAuthenticationFilter();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf()
				.disable()
				.formLogin()
				.disable()
				.httpBasic()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint())
					.and()
					.authorizeRequests()
						.antMatchers(
							"/api/auth/signup",
							"/api/auth/login",
							"/v3/**",
							"/docs-disney/**",
							"/docs-disney.yaml",
							"/swagger-ui/**"
							).permitAll()
					.anyRequest()
					.authenticated();

		http.addFilterBefore(createTokenAuthFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	 public OpenAPI customOpenAPI() {
		   return new OpenAPI()
		          .components(new Components()
		          .addSecuritySchemes("Bearer",
		          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));
		}

}
