package com.weborder.service.impl;

import com.weborder.dao.UserAccountMapper;
import com.weborder.entity.UserAccount;
import com.weborder.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	private final UserAccountMapper userAccountMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserAccountServiceImpl(UserAccountMapper userAccountMapper,PasswordEncoder passwordEncoder) {
		this.userAccountMapper = userAccountMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void createUser(UserAccount userAccount) {
		userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
		userAccountMapper.insertUser(userAccount);
	}
	
	@Override
	public UserAccount getUserById(Integer userId) {
		return userAccountMapper.getUserById(userId);
	}
	
	@Override
	public List<UserAccount> getAllUsers() {
		return userAccountMapper.getAllUsers();
	}
	
	@Override
	public void updateUser(UserAccount userAccount) {
		userAccountMapper.updateUser(userAccount);
	}
	
	@Override
	public void deleteUser(Integer userId) {
		userAccountMapper.deleteUser(userId);
	}
	
	@Override
	public boolean validateUserCredentials(String userName, String password) {
		// 假设用户名是唯一的，可以通过名称查询用户并验证密码
		// 需要在 UserAccountMapper 中添加一个根据名称查询用户的方法
		UserAccount user = userAccountMapper.getUserByName(userName);
		if (user != null) {
			return user.getPassword().equals(password);
		}
		return false;
	}
}
