package com.weborder.service;

import com.weborder.entity.ShoppingCart;
import com.weborder.entity.CartItem;

import java.util.List;

public interface ShoppingCartService {
	
	/**
	 * 创建新的购物车
	 *
	 * @param cart 要创建的购物车信息
	 */
	void createCart(ShoppingCart cart);
	
	/**
	 * 根据购物车ID获取购物车信息
	 *
	 * @param cartId 购物车ID
	 * @return 返回购物车信息
	 */
	ShoppingCart getCartById(Integer cartId);
	
	/**
	 * 更新购物车信息
	 *
	 * @param cart 要更新的购物车信息
	 */
	void updateCart(ShoppingCart cart);
	
	/**
	 * 清空购物车
	 *
	 * @param cartId 购物车ID
	 */
	void clearCart(Integer cartId);
	
	/**
	 * 删除购物车
	 *
	 * @param cartId 购物车ID
	 */
	void deleteCart(Integer cartId);
	
	/**
	 * 获取购物车中的所有项目
	 *
	 * @param cartId 购物车ID
	 * @return 返回购物车项目列表
	 */
	List<CartItem> getCartItemsByCartId(Integer cartId);
	
	/**
	 * 添加项目到购物车并更新购物车
	 *
	 * @param cartId	购物车ID
	 * @param cartItem	物品项
	 */
	void addItemToCart(Integer cartId,CartItem cartItem);
	
	/**
	 * 从购物车中删除项目并更新购物车
	 *
	 * @param cartId	购物车ID
	 * @param itemId	物品项ID
	 */
	void removeItemFromCart(Integer cartId,Integer itemId);
}
