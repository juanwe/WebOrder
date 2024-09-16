package com.weborder.service;

import com.weborder.entity.CartItem;

import java.util.List;

public interface CartItemService {
	
	// 添加购物篮项目
	void addCartItem(CartItem cartItem);
	
	// 根据购物篮ID获取所有项目
	List<CartItem> getCartItemsByCartId(Integer cartId);
	
	// 更新购物篮项目
	void updateCartItem(CartItem cartItem);
	
	// 删除购物篮项目
	void deleteCartItem(Integer itemId);
}
