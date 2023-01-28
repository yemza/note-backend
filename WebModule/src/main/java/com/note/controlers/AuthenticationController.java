package com.note.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.note.modules.JwtRequest;
import com.note.modules.JwtResponse;
import com.note.services.AuthService;
import com.note.services.UserService;
import com.note.utils.TokenUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@CrossOrigin(maxAge = 3600)
@Api(value = "AuthenticationController", tags = { "Authentication Controller" })
@SwaggerDefinition(tags = { @Tag(name = "Authentication Controller", description = "Write description here") })
@RestController
public class AuthenticationController {
	
	
	@Autowired
	UserService	userService;
	
	@Autowired
	AuthService	authService;
	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	TokenUtility tokenUtility;
	
	@PostMapping("/authenticate")
	public JwtResponse Authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		
	    final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword())	
					);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails =  authService.loadUserByUsername(jwtRequest.getUsername());
		Long userId =  userService.loadUserIdByUsername(jwtRequest.getUsername());
	return new JwtResponse(userId,userDetails.getUsername(),tokenUtility.generateAccessToken(userDetails,userId));
	}
		
}
