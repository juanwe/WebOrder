package com.weborder.controller;

import com.weborder.entity.UserAccount;
import com.weborder.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
public class UserAccountController {
	
	private final UserAccountService userAccountService;
	
	private final String jwtSecret = "SecretKeyToGenJWTs"; // JWT密钥
	
	@Autowired
	public UserAccountController(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}
	
	// 用户注册
	@PostMapping("/register")
	public ResponseEntity<Void> createUser(@RequestBody UserAccount userAccount) {
		userAccountService.createUser(userAccount);
		return ResponseEntity.ok().build();
	}
	
	// 用户登录验证
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String userName, @RequestParam String password) {
		// 获取用户信息
		UserAccount user = userAccountService.getUserByName(userName);
		
		// 用于返回响应的 Map
		Map<String, Object> response = new HashMap<>();
		
		if (user == null) {
			// 如果用户不存在，返回"未知用户"信息
			response.put("message", "Unknown user");
			return ResponseEntity.status(401).body(response);
		}
		
		// 验证密码是否正确
		boolean isValidPassword = user.getPassword().equals(password);
		
		if (!isValidPassword) {
			// 如果密码错误，返回"密码错误"信息
			response.put("message", "Incorrect password");
			return ResponseEntity.status(401).body(response);
		}
		
		// 返回用户ID和登录成功信息
		response.put("userId", user.getUserId());  // 返回用户ID
		response.put("message", "Login successful");
		// response.put("token", token);  // 如果使用JWT，返回令牌
		
		return ResponseEntity.ok(response);
	}
	
	
	
	// 根据ID获取用户信息
	@GetMapping("/{userId}")
	public ResponseEntity<UserAccount> getUserById(@PathVariable Integer userId) {
		UserAccount user = userAccountService.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	
	// 获取所有用户
	@GetMapping
	public ResponseEntity<List<UserAccount>> getAllUsers() {
		List<UserAccount> users = userAccountService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	// 更新用户信息
	@PutMapping("/{userId}")
	public ResponseEntity<Void> updateUser(@PathVariable Integer userId, @RequestBody UserAccount userAccount) {
		userAccount.setUserId(userId);
		userAccountService.updateUser(userAccount);
		return ResponseEntity.ok().build();
	}
	
	// 删除用户
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
		userAccountService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}
}
