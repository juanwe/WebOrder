package com.weborder.entity;

import lombok.Data;

@Data
public class UserAccount {
	private Integer userId;     // 用户ID
	private String name;        // 用户姓名
	private String password;    // 用户密码
}
