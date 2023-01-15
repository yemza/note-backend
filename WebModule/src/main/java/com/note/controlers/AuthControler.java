package com.note.controlers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.JwtClaimsSet.Builder;


@RestController
public class AuthControler {

	
	
	private JwtEncoder jwtEncoder;

	public AuthControler(JwtEncoder jwtEncoder) {
		super();
		this.jwtEncoder = jwtEncoder;
	}
	
	
	@PostMapping("/token")
	public Map<String , String> jwtToken(Authentication authentication){
		Map<String , String> idToken= new HashMap<>();
		Instant instant = Instant.now();
		String scope = authentication.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(""));
		
		JwtClaimsSet  jwtClaimsSet = JwtClaimsSet.builder()
				       .subject(authentication.getName())
				       .issuedAt(instant)
				       .expiresAt(instant.plus(20,ChronoUnit.MINUTES))
				       .issuer("security-service")
				       .claim("scop", scope)
				       .build();
		   String jwtRefreshToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
           idToken.put("refreshToken",jwtRefreshToken);
		return idToken;
		
	}
	
	
}
