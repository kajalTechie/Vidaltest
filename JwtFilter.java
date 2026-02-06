package com.vidal.vidal.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.vidal.vidal.security.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/auth/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

}
