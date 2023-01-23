package com.note.services;

import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {

	UserDetails loadUserByUsername(String username);

}
