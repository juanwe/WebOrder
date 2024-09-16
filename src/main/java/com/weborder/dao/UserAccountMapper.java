package com.weborder.dao;

import com.weborder.entity.UserAccount;
import com.weborder.service.impl.UserAccountServiceImpl;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserAccountMapper {
	
	// 插入新用户
	@Insert("INSERT INTO UserAccount(Name, Password) VALUES(#{name}, #{password})")
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	void insertUser(UserAccount userAccount);
	
	// 根据ID查询用户
	@Select("SELECT * FROM UserAccount WHERE UserID = #{userId}")
	UserAccount getUserById(Integer userId);
	
	// 查询所有用户
	@Select("SELECT * FROM UserAccount")
	List<UserAccount> getAllUsers();
	
	// 更新用户信息
	@Update("UPDATE UserAccount SET Name = #{name}, Password = #{password} WHERE UserID = #{userId}")
	void updateUser(UserAccount userAccount);
	
	// 删除用户
	@Delete("DELETE FROM UserAccount WHERE UserID = #{userId}")
	void deleteUser(Integer userId);
	
	@Select("SELECT * FROM useraccount WHERE Name = #{name}")
	UserAccount getUserByName(String name);
}
