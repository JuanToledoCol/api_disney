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
						"/login",
						"/signup"
						)
					.permitAll()
					.anyRequest()
					.authenticated();
		
		http.addFilterBefore(createTokenAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

}
