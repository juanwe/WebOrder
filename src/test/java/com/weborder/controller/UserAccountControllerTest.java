package com.weborder.controller;

import com.weborder.entity.UserAccount;
import com.weborder.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserAccountController.class)
class UserAccountControllerTest {
	
	@Autowired
	private MockMvc mockMvc; // 模拟HTTP请求
	
	@Mock
	private UserAccountService userAccountService; // 模拟服务层
	
	@MockBean
	private UserDetailsService userDetailsService; // 模拟UserDetailsService
	
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
}
