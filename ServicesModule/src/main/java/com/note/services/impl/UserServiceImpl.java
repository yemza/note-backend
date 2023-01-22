package com.note.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.entitiesDAO.UserEntityDAO;
import com.note.repository.UserEntityDAORepository;
import com.note.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserEntityDAORepository userEntityDAORepository;			 	
		
		
		
	@Override
	public UserEntityDAO loadUserByUsername(String username) {
		return userEntityDAORepository.findByUsername(username);
	}

}
