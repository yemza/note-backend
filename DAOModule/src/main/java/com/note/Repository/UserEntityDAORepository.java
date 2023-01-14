package com.note.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.EntitiesDAO.UserEntityDAO;

public interface UserEntityDAORepository extends JpaRepository<UserEntityDAO, Long> {

}
