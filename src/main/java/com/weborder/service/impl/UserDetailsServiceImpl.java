package com.weborder.service.impl;

import com.weborder.entity.UserAccount;
import com.weborder.dao.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserAccountMapper userAccountMapper;
	
	@Autowired
	public UserDetailsServiceImpl(UserAccountMapper userAccountMapper) {
		this.userAccountMapper = userAccountMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userAccountMapper.getUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		// 创建UserDetails对象
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		return new User(user.getName(), user.getPassword(), Collections.singleton(authority));
	}
}
