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
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
