package com.note.filters;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	 
	AuthenticationManager authenticationManager;
	
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager =authenticationManager;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("attemptAuthentication Start");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
		         = new UsernamePasswordAuthenticationToken(username, password);
		
		System.out.println("attemptAuthentication End");

		return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		System.out.println("successfulAuthentication Start");
        User user = (User) authResult.getPrincipal();
        Algorithm algoAcces = Algorithm.HMAC256("NoteSecret12345");
        
        String jwtAccessToken = JWT.create()
        		.withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
        		.withIssuer(request.getRequestURL().toString())
        		.withClaim("roles", user.getAuthorities().stream().map(role-> role.getAuthority()).collect(Collectors.toList()))
        		.sign(algoAcces);
        
        
        String jwtRefreshToken = JWT.create()
        		.withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+15*60*1000))
        		.withIssuer(request.getRequestURL().toString())
        		.sign(algoAcces);
        
        Map<String, String> idToken = new HashMap<>();
        idToken.put("access-token", jwtAccessToken);
        idToken.put("refresh-token", jwtRefreshToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        
        
        
        
        
        
		System.out.println("successfulAuthentication End");

	}

}
