package com.note.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.note.repository.UserEntityDAORepository;
import com.note.services.UserService;


@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	UserEntityDAORepository userEntityDAORepository;
	
	
	@Override
	public Long loadUserIdByUsername(String username) {
		return userEntityDAORepository.findByUsername(username).getUserId();
	}
	



}
