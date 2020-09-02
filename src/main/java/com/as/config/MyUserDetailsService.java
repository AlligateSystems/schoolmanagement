package com.as.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String name) {
		UserEntity user = repo.findByUsername(name);
		if (user == null)
			System.out.println("User Not Found");
		return new UserPrincipal(user);
	}

}
