package com.note.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.note.entitiesDAO.UserEntityDAO;
import com.note.repository.UserEntityDAORepository;
import com.note.services.UserService;

public class AuthServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserEntityDAORepository userEntityDAORepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntityDAO userEntityDAO = userEntityDAORepository.findByUsername(username);
		if (userEntityDAO == null)
			throw new UsernameNotFoundException("invalid user");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		userEntityDAO.getUserRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new User(userEntityDAO.getUsername(), userEntityDAO.getPassword(), authorities);

	}

}
