package com.note.services;

import java.util.List;

import com.note.entitiesDAO.NoteEntityDAO;

public interface NoteService {

	
	 NoteEntityDAO getNoteById(Long noteId);
	 NoteEntityDAO getNoteByUserId(Long noteId , Long userId);
     List<NoteEntityDAO> getAllNoteByUserId(Long userId);
	 NoteEntityDAO saveNewNote(NoteEntityDAO newNote);
}
