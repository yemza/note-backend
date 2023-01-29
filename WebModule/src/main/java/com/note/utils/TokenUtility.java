package com.note.utils;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;



@Component
public class TokenUtility {
	
	private Long TOKEN_VALIDITY= 604800L;
	private Algorithm TOKEN_SECRET = Algorithm.HMAC256("NoteSecret12345");

	
	public String generateAccessToken ( UserDetails user , Long userId) {
		
	 String jwtAccessToken = JWT.create()
        		.withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+100000*60*1000))
        		.withClaim("roles", user.getAuthorities().stream().map(role-> role.getAuthority()).collect(Collectors.toList()))
        		.withClaim("userId", userId)
        		.sign(TOKEN_SECRET);
	 return jwtAccessToken;
		
	}
	
	public String generateRefreshToken ( UserDetails user) {
	 String jwtAccessToken = JWT.create()
       		.withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
       		.sign(TOKEN_SECRET);
	 return jwtAccessToken;
		
	}
	
	public String getUserNameFromToken(String token) {
		try {
			  String username = JWT.decode(token).getSubject();
			  return username;
		    			
		} catch (Exception e) {
			return null;
		}
	}

	private Date generateExparationDate() {
		return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
	}

	public boolean isTokenValid(String token, UserDetails user) {
		String username = getUserNameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		Date expiration= JWT.decode(token).getExpiresAt();
		return expiration.before(new Date());
	}
	
	private Map<String,Claim>  getClaims(String token) {
		
		Map<String,Claim> claims;
		try {
		 claims = JWT.decode(token).getClaims();
			
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}


}
