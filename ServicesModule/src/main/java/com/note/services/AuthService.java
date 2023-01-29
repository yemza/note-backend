package com.note.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
	
	UserDetails loadUserByUsername(String username);

}
