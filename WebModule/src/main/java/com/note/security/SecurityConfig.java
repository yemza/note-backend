package com.note.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.security.core.userdetails.User;


@Configurable
@EnableWebSecurity
public class SecurityConfig {

	
	private RsakeysConfig rsakeysConfig;
	
	

    public SecurityConfig(RsakeysConfig rsakeysConfig) {
		this.rsakeysConfig = rsakeysConfig;
	}

	@Bean
    public UserDetailsService inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(("{noop}1234")).authorities("USER").build(),
                User.withUsername("user2").password(("{noop}1234")).authorities("USER").build(),
                User.withUsername("admin").password(("{noop}1234")).authorities("USER","ADMIN").build()
        );
    }
    
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
	return http
			.csrf(csrf -> csrf.disable())
		    .authorizeRequests(auth -> auth.anyRequest().authenticated())
		    .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
		    .httpBasic(Customizer.withDefaults())
		    .build();
		
		
	}
	
	
    @Bean
    JwtDecoder jwtDecoder(){
     return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
    }
    
    
    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk= new RSAKey.Builder(rsakeysConfig.publicKey()).privateKey(rsakeysConfig.privateKey()).build();
        JWKSource<SecurityContext> jwkSource= new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }
	
}
