package com.weborder.dao;

import com.weborder.entity.CartItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartItemMapper {
	
	// 插入购物篮项目
	@Insert("INSERT INTO CartItem(CartID, ProductID, Quantity) VALUES(#{cartId}, #{productId}, #{quantity})")
	@Options(useGeneratedKeys = true, keyProperty = "itemId")
	void insertCartItem(CartItem cartItem);
	
	// 根据购物篮ID查询项目
	@Select("SELECT * FROM CartItem WHERE CartID = #{cartId}")
	List<CartItem> getCartItemsByCartId(Integer cartId);
	
	// 更新购物篮项目
	@Update("UPDATE CartItem SET Quantity = #{quantity} WHERE ItemID = #{itemId}")
	void updateCartItem(CartItem cartItem);
	
	// 删除购物篮项目
	@Delete("DELETE FROM CartItem WHERE ItemID = #{itemId}")
	void deleteCartItem(Integer itemId);
}
