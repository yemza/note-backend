package com.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.entitiesDAO.UserEntityDAO;

public interface UserEntityDAORepository extends JpaRepository<UserEntityDAO, Long> {

	UserEntityDAO findByUsername(String username);

}
