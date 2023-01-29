package com.note.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.entitiesDAO.NoteEntityDAO;
import com.note.repository.NoteEntityDAORepository;
import com.note.services.NoteService;


@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	NoteEntityDAORepository noteEntityDAORepository;
	
	

	@Override
	public NoteEntityDAO getNoteById(Long noteId) {
		// TODO Auto-generated method stub
		return noteEntityDAORepository.findById(noteId).get();
	}



	@Override
	public NoteEntityDAO getNoteByUserId(Long noteId, Long userId) {
		// TODO Auto-generated method stub
		return noteEntityDAORepository.findByNoteIdAndNoteUserUserId(noteId,userId);
	}



	@Override
	public List<NoteEntityDAO> getAllNoteByUserId(Long userId) {
		// TODO Auto-generated method stub
		return noteEntityDAORepository.findAllByNoteUserUserId(userId);
	}



	@Override
	public NoteEntityDAO saveNewNote(NoteEntityDAO newNote) {
		
		return noteEntityDAORepository.save(newNote);
	}

}
