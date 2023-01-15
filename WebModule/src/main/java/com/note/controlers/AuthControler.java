package com.note.controlers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.JwtClaimsSet.Builder;


@RestController
public class AuthControler {

	
	
	private JwtEncoder jwtEncoder;
	private JwtDecoder jwtDecoder;
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;

	public AuthControler(JwtEncoder jwtEncoder,  AuthenticationManager authenticationManager , JwtDecoder jwtDecoder,
			UserDetailsService userDetailsService) {
		this.jwtEncoder = jwtEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtDecoder = jwtDecoder;
		this.userDetailsService =userDetailsService;
	}
	
	
	@PostMapping("/token")
	public ResponseEntity<Map<String, String>> jwtToken( 
			String grantType,
            String username,
            String password,
            boolean withRefreshToken,
            String refreshToken){
		
		
		String subject=null;
        String scope=null;
		
        if(grantType.equals("password")){

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));	
				 subject=authentication.getName();
		         scope=authentication.getAuthorities()
		                 .stream().map(aut -> aut.getAuthority())
		                 .collect(Collectors.joining(" "));
		
        }else if(grantType.equals("refreshToken")){        	
        	if(refreshToken==null) {
                return new ResponseEntity<>(Map.of("errorMessage","Refresh  Token is required"), HttpStatus.UNAUTHORIZED);
            }
            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
        	
        	subject =decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope=authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));

        }
		
		Map<String , String> idToken= new HashMap<>();
		Instant instant = Instant.now();		
		JwtClaimsSet  jwtClaimsSet = JwtClaimsSet.builder()
				       .subject(subject)
				       .issuedAt(instant)
				       .expiresAt(instant.plus(withRefreshToken?20:60,ChronoUnit.MINUTES))
				       .issuer("security-service")
				       .claim("scop", scope)
				       .build();
		   String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
           idToken.put("accessToken",jwtAccessToken);
           
           
           if(withRefreshToken) {
        	   JwtClaimsSet  jwtClaimsSetRefresh = JwtClaimsSet.builder()
				       .subject(subject)
				       .issuedAt(instant)
				       .expiresAt(instant.plus(60,ChronoUnit.MINUTES))
				       .issuer("security-service")
				       .build();
		   String jwtRefreshToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
           idToken.put("refreshToken",jwtRefreshToken);
           
           }
		return  new ResponseEntity<>(idToken,HttpStatus.OK);
		
	}
	
	
}
