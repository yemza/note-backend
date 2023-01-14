package com.note.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(tags = {@Tag(name="test API" , description="test cotroller")})
@Api(value = "testControle" , tags= {"contoller test"})
@RestController
public class test {
	
	
    @GetMapping("/hello")
	public String test() {
		return "hello application woking fine";
	}

}
