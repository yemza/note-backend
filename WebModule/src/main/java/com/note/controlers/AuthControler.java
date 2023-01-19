package com.note.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.note.modules.JwtRequest;
import com.note.modules.JwtResponse;
import com.note.services.impl.JwtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;


@Api(value = "Notes controler" ,tags = {"Notes controler"})
@SwaggerDefinition(tags = {@Tag(name = "Notes controler", description = "Notes controler")})
@RestController
public class AuthControler {
	
	    @Autowired
	    private JwtService jwtService;

	    @PostMapping({"/authenticate"})
	    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
	        return jwtService.createJwtToken(jwtRequest);
	    }
}
