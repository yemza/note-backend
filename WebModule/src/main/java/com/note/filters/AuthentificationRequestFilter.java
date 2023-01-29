package com.note.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.note.services.AuthService;
import com.note.services.impl.AuthServiceImpl;
import com.note.utils.TokenUtility;

public class AuthentificationRequestFilter extends OncePerRequestFilter {

	private final String AUTHORIZATION_HEADER = "Authorization";

	@Autowired
	private TokenUtility tokenUtility;


	@Autowired
	private AuthService authService;
	

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		// get Token From header
		final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
		final SecurityContext securityContext = SecurityContextHolder.getContext();

		// make sure Token is Valide

		if (authorizationHeader != null && securityContext.getAuthentication() == null) {
			String token = authorizationHeader.substring("Bearer ".length());
			String username = tokenUtility.getUserNameFromToken(token);
			if (username != null) {
				UserDetails user = authService.loadUserByUsername(username);
				if (tokenUtility.isTokenValid(token, user)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, user.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}

			}

		}

		filterChain.doFilter(request, response);
	}

}