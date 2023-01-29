package com.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.entitiesDAO.NoteEntityDAO;

public interface NoteEntityDAORepository extends JpaRepository<NoteEntityDAO, Long>{

	List<NoteEntityDAO> findAllByNoteUserUserId(Long userId);
	
	NoteEntityDAO findByNoteIdAndNoteUserUserId(Long noteId, Long userId);

}
