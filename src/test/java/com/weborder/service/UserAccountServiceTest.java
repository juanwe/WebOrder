package com.weborder.service;

import com.weborder.dao.UserAccountMapper;
import com.weborder.entity.UserAccount;
import com.weborder.service.impl.UserAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAccountServiceTest {
	
	@Mock
	private UserAccountMapper userAccountMapper; // 模拟数据访问层
	
	@Mock
	private PasswordEncoder passwordEncoder; // 模拟密码加密
	
	@InjectMocks
	private UserAccountServiceImpl userAccountService; // 被测试的服务类
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // 初始化 Mockito
	}
	
	@Test
	void testCreateUser() {
		// 模拟用户注册的输入
		UserAccount user = new UserAccount();
		user.setName("testUser");
		user.setPassword("password");
		
		// 模拟加密后的密码
		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
		
		// 模拟数据库保存操作
		doNothing().when(userAccountMapper).insertUser(user);
		
		// 调用服务层的用户创建方法
		userAccountService.createUser(user);
		
		// 验证密码是否加密
		//verify(passwordEncoder).encode("password");
		
		// 验证是否调用了数据库插入方法
		verify(userAccountMapper).insertUser(user);
		
		// 验证用户密码已加密
		assertEquals("encodedPassword", user.getPassword());
	}
}
