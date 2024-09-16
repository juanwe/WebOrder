package com.weborder.service;

import com.weborder.entity.CartItem;

import java.util.List;

public interface CartItemService {
	
	/**
	 * 添加购物车项目
	 *
	 * @param cartItem 要添加的购物车项目
	 */
	void addCartItem(CartItem cartItem);
	
	/**
	 * 根据购物车ID获取购物车中的项目
	 *
	 * @param cartId 购物车ID
	 * @return 返回购物车项目列表
	 */
	List<CartItem> getCartItemsByCartId(Integer cartId);
	
	/**
	 * 更新购物车项目
	 *
	 * @param cartItem 要更新的购物车项目
	 */
	void updateCartItem(CartItem cartItem);
	
	/**
	 * 删除购物车项目
	 *
	 * @param itemId 购物车项目ID
	 */
	void deleteCartItem(Integer itemId);
}
