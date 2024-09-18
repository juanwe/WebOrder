package com.weborder.dao;

import com.weborder.entity.CartItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartItemMapper {
	
	// 插入购物篮项目
	@Insert("INSERT INTO CartItem(CartID, ProductID, Quantity,Price,ProductWeight) VALUES(#{cartId}, #{productId}, #{quantity}, #{price},#{productWeight})")
	@Options(useGeneratedKeys = true, keyProperty = "itemId")
	void insertCartItem(CartItem cartItem);
	
	// 根据购物篮ID查询项目
	@Select("SELECT * FROM CartItem WHERE CartID = #{cartId}")
	List<CartItem> getCartItemsByCartId(Integer cartId);
	
	// 根据项目ID查询项目
	@Select("SELECT * FROM cartitem where ItemID = #{cartId}")
	CartItem getCartItemById(Integer cartId);
	// 更新购物篮项目
	@Update("UPDATE CartItem SET Quantity = #{quantity} WHERE ItemID = #{itemId}")
	void updateCartItem(CartItem cartItem);
	
	// 删除购物篮项目
	@Delete("DELETE FROM CartItem WHERE ItemID = #{itemId}")
	void deleteCartItem(Integer itemId);
	
	//根据购物篮ID删除项目
	@Delete("DELETE FROM cartitem where CartID = #{cartId}")
	void deleteCartItemsByCartId(Integer cartId);
}
