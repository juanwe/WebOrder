package com.weborder.controller;

import com.weborder.entity.UserAccount;
import com.weborder.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserAccountController.class)
class UserAccountControllerTest {
	
	@Autowired
	private MockMvc mockMvc; // 模拟HTTP请求
	
	@MockBean
	private UserAccountService userAccountService; // 使用 @MockBean 模拟服务层
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // 初始化 Mockito
	}
	
	@Test
	void testCreateUser() throws Exception {
		// 模拟服务层的用户创建操作
		doNothing().when(userAccountService).createUser(new UserAccount());
		
		// 模拟注册API请求
		mockMvc.perform(post("/api/users/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"name\": \"testUser\", \"password\": \"password\" }"))
				.andExpect(status().isOk());
	}
	
	@Test
	void testLoginSuccess() throws Exception {
		// 模拟服务层返回用户验证通过
		when(userAccountService.validateUserCredentials(anyString(), anyString())).thenReturn(true);
		
		// 发送模拟登录请求
		mockMvc.perform(post("/api/users/login")
						.param("username", "testUser")
						.param("password", "password")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk())  // 验证返回 200 OK
				.andExpect(content().string(org.hamcrest.Matchers.containsString(".")));  // 验证返回体中有JWT（通过检查JWT的结构：Header.Payload.Signature）
	}
	
	@Test
	void testLoginFailure() throws Exception {
		// 模拟服务层返回用户验证失败
		when(userAccountService.validateUserCredentials(anyString(), anyString())).thenReturn(false);
		
		// 发送模拟登录请求
		mockMvc.perform(post("/api/users/login")
						.param("username", "invalidUser")
						.param("password", "invalidPassword")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isUnauthorized());  // 验证返回 401 Unauthorized
	}
	
	// 测试根据ID获取用户
	@Test
	@WithMockUser
	void testGetUserById() throws Exception {
		UserAccount user = new UserAccount();
		user.setUserId(1);
		user.setName("testUser");
		
		when(userAccountService.getUserById(anyInt())).thenReturn(user);
		
		mockMvc.perform(get("/api/users/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value(1))
				.andExpect(jsonPath("$.name").value("testUser"));
	}
	
	// 测试获取所有用户
	@Test
	@WithMockUser
	void testGetAllUsers() throws Exception {
		UserAccount user1 = new UserAccount();
		user1.setUserId(1);
		user1.setName("testUser1");
		
		UserAccount user2 = new UserAccount();
		user2.setUserId(2);
		user2.setName("testUser2");
		
		List<UserAccount> users = Arrays.asList(user1, user2);
		
		when(userAccountService.getAllUsers()).thenReturn(users);
		
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].userId").value(1))
				.andExpect(jsonPath("$[0].name").value("testUser1"))
				.andExpect(jsonPath("$[1].userId").value(2))
				.andExpect(jsonPath("$[1].name").value("testUser2"));
	}
	
	// 测试更新用户
	@Test
	@WithMockUser
	void testUpdateUser() throws Exception {
		doNothing().when(userAccountService).updateUser(any(UserAccount.class));
		
		mockMvc.perform(put("/api/users/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"name\": \"updatedUser\" }"))
				.andExpect(status().isOk());
	}
	
	// 测试删除用户
	@Test
	@WithMockUser
	void testDeleteUser() throws Exception {
		doNothing().when(userAccountService).deleteUser(anyInt());
		
		mockMvc.perform(delete("/api/users/1"))
				.andExpect(status().isOk());
	}
}
