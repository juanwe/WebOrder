package com.weborder.dao;

import com.weborder.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShoppingCartMapper {
	
	// 插入新购物篮
	@Insert("INSERT INTO ShoppingCart(UserID, TotalPrice, TotalWeight) VALUES(#{userId}, #{totalPrice}, #{totalWeight})")
	@Options(useGeneratedKeys = true, keyProperty = "cartId")
	void insertCart(ShoppingCart shoppingCart);
	
	// 根据ID查询购物篮
	@Select("SELECT * FROM ShoppingCart WHERE CartID = #{cartId}")
	ShoppingCart getCartById(Integer cartId);
	
	// 更新购物篮
	@Update("UPDATE ShoppingCart SET TotalPrice = #{totalPrice}, TotalWeight = #{totalWeight} WHERE CartID = #{cartId}")
	void updateCart(ShoppingCart shoppingCart);
	
	// 删除购物篮
	@Delete("DELETE FROM ShoppingCart WHERE CartID = #{cartId}")
	void deleteCart(Integer cartId);
}
