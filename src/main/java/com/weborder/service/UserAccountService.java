package com.weborder.service;

import com.weborder.entity.UserAccount;

import java.util.List;

public interface UserAccountService {
	
	/**
	 * 创建新用户（注册）
	 *
	 * @param userAccount 要创建的用户信息
	 */
	void createUser(UserAccount userAccount);
	
	/**
	 * 根据用户ID获取用户信息
	 *
	 * @param userId 用户ID
	 * @return 返回用户信息
	 */
	UserAccount getUserById(Integer userId);
	
	/**
	 * 获取所有用户
	 *
	 * @return 返回所有用户的列表
	 */
	List<UserAccount> getAllUsers();
	
	/**
	 * 更新用户信息
	 *
	 * @param userAccount 要更新的用户信息
	 */
	void updateUser(UserAccount userAccount);
	
	/**
	 * 删除用户
	 *
	 * @param userId 用户ID
	 */
	void deleteUser(Integer userId);
	
	/**
	 * 用户登录验证
	 *
	 * @param userName 用户名
	 * @param password 密码
	 * @return 验证通过返回 true，否则返回 false
	 */
	boolean validateUserCredentials(String userName, String password);
}
