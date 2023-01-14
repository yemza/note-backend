package com.note.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.EntitiesDAO.NoteEntityDAO;

public interface NoteEntityDAORepository extends JpaRepository<NoteEntityDAO, Long>{

}
