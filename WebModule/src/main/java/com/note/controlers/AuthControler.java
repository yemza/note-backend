package com.note.controlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.entitiesDAO.NoteEntityDAO;
import com.note.filters.JwtAuthenticationFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(value = "Notes controler", tags = { "Notes controler" })
@SwaggerDefinition(tags = { @Tag(name = "Notes controler", description = "Notes controler") })
@RestController
public class AuthControler {

	/**
	 * get a note by Id
	 */
	@PostMapping("/token")
	public NoteEntityDAO token(Long noteId) {
		return null;
	}
	/**
	 * get a note by Id
	 */
	@GetMapping("/refreshToken")
	public void refreshToken(HttpServletResponse response , HttpServletRequest request) {
     
		String authToken = request.getHeader("Authorization");

	}


}
