package com.note.security;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import com.note.filters.AuthentificationRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String[] PUPLIC_ENDPOINTS = {
			"/authenticate"
	};
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
  
    	 http
    	.cors().configurationSource(request -> {
    		  CorsConfiguration cors = new CorsConfiguration();
    	      cors.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:80", "http://example.com"));
    	      cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
    	      cors.setAllowedHeaders(List.of("*"));
    	      return cors;
    	    })
    	.and()
    	.csrf()
    	.disable()
    	.sessionManagement()
    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
    	.authorizeRequests()
    	.antMatchers(PUPLIC_ENDPOINTS)
    	.permitAll()
    	.anyRequest()
    	.authenticated()
    	.and()
    	.addFilterBefore(authentificationRequestFilter() , 
    			         UsernamePasswordAuthenticationFilter.class);
    	
    }
    
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public AuthentificationRequestFilter authentificationRequestFilter() {
    	return new AuthentificationRequestFilter();

	}
    

    
}