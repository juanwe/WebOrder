package com.weborder.controller;

import com.weborder.entity.UserAccount;
import com.weborder.service.UserAccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
		// 获取用户信息
		UserAccount user = userAccountService.getUserByName(username);
		
		if (user == null) {
			// 如果用户不存在，返回"未知用户"信息
			return ResponseEntity.status(401).body("Unknown user");
		}
		
		// 验证密码是否正确
		boolean isValidPassword = user.getPassword().equals(password);
		
		if (!isValidPassword) {
			// 如果密码错误，返回"密码错误"信息
			return ResponseEntity.status(401).body("Incorrect password");
		}
		
		// 如果用户名和密码正确，生成JWT
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10天有效
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		
		// 返回JWT给客户端
		return ResponseEntity.ok(token);
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
