package com.note.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.note.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(value = "Notes controler" ,tags = {"Notes controler"})
@SwaggerDefinition(tags = {@Tag(name = "Notes controler", description = "Notes controler")})
@RestController
public class UserControler {

	
	@Autowired
	UserService userService;
	
//	@GetMapping("/getUserByUsername")
//	public UserEntityDAO getUserByUsername(String username) {
//		return userService.loadUserEntityDAOByUsername(username);
//		
//	}
	
}
