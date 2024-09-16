package com.weborder.service;

import com.weborder.entity.ShoppingCart;

public interface ShoppingCartService {
	
	// 创建新的购物篮
	void createCart(ShoppingCart cart);
	
	// 根据ID获取购物篮
	ShoppingCart getCartById(Integer cartId);
	
	// 更新购物篮信息
	void updateCart(ShoppingCart cart);
	
	// 删除购物篮
	void deleteCart(Integer cartId);
}
