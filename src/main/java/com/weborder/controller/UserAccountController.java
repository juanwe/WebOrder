package com.weborder.controller;

import com.weborder.entity.UserAccount;
import com.weborder.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {
	
	private final UserAccountService userAccountService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserAccountController(UserAccountService userAccountService,PasswordEncoder passwordEncoder) {
		this.userAccountService = userAccountService;
		this.passwordEncoder = passwordEncoder;
	}
	
	// 用户注册
	@PostMapping("/register")
	public ResponseEntity<Void> createUser(@RequestBody UserAccount userAccount) {
		userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
		userAccountService.createUser(userAccount);
		return ResponseEntity.ok().build();
	}
	
	/*// 用户登录验证
	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
		boolean isValid = userAccountService.validateUserCredentials(username, password);
		return ResponseEntity.ok(isValid);
	}*/
	
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
