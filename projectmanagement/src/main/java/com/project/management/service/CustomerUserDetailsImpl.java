package com.project.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.management.model.User;
import com.project.management.model.UserPrincipal;
import com.project.management.repository.UserRepository;

@Service
public class CustomerUserDetailsImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepo.findByEmail(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("Username not found"+username);
		}
		
		List<GrantedAuthority> auth = new ArrayList<>();
		
		return new UserPrincipal(user);
	}
}
