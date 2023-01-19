package com.note.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.entitiesDAO.NoteEntityDAO;
import com.note.services.NoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(value = "Notes controler" ,tags = {"Notes controler"})
@SwaggerDefinition(tags = {@Tag(name = "Notes controler", description = "Notes controler")})
@RestController
public class NoteControler {

	@Autowired 
	NoteService noteService;
	
	
	/**
	 * get a note by Id
	 * */
	@GetMapping("/getNoteById")
	public NoteEntityDAO getNoteById(Long noteId) {
       return noteService.getNoteById(noteId);
	}
	
	
	
	/**
	 * get a note by user Id
	 * */
	@GetMapping("/getNoteByUserId")
	public NoteEntityDAO getNoteByUserId(Long noteId , Long userId) {
       return noteService.getNoteByUserId(noteId, userId);
	}
	
	
	
	/**
	 * get All note by user Id
	 * */
	@GetMapping("/getAllNoteByUserId")
	public List<NoteEntityDAO> getAllNoteByUserId(Long userId) {
       return noteService.getAllNoteByUserId(userId);
	}
	
	
	
	
}
