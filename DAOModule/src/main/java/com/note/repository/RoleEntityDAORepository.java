package com.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.entitiesDAO.RoleEntityDAO;

public interface RoleEntityDAORepository extends JpaRepository<RoleEntityDAO, Long>{
  
	RoleEntityDAO findByRoleName(String UserName);

}
