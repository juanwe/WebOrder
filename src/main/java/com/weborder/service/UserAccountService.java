package com.weborder.service;

import com.weborder.entity.UserAccount;

import java.util.List;

public interface UserAccountService {
	
	// 创建新用户
	void createUser(UserAccount userAccount);
	
	// 根据ID获取用户
	UserAccount getUserById(Integer userId);
	
	// 获取所有用户
	List<UserAccount> getAllUsers();
	
	// 更新用户信息
	void updateUser(UserAccount userAccount);
	
	// 删除用户
	void deleteUser(Integer userId);
	
	// 用户登录验证
	boolean validateUserCredentials(String userName, String password);
}
