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
	 * 根据ID查询项目
	 *
	 * @param itemId 项目ID
	 * @return 返回对应项信息
	 */
	CartItem getCartItemById(Integer itemId);
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
	
	/**
	 * 根据购物车ID删除项目
	 * @param cartId 购物车ID
	 */
	void deleteCartItemsByCartId(Integer cartId);
}
