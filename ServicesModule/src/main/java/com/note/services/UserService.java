package com.note.services;

import com.note.entitiesDAO.UserEntityDAO;

public interface UserService {

	UserEntityDAO loadUserByUsername(String username);
	
}
