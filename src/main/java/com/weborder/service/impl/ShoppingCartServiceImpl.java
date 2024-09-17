package com.weborder.service.impl;

import com.weborder.dao.ShoppingCartMapper;
import com.weborder.entity.CartItem;
import com.weborder.entity.ShoppingCart;
import com.weborder.service.ShoppingCartService;
import com.weborder.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	private final ShoppingCartMapper shoppingCartMapper;
	private final CartItemService cartItemService;
	
	@Autowired
	public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper, CartItemService cartItemService) {
		this.shoppingCartMapper = shoppingCartMapper;
		this.cartItemService = cartItemService;
	}
	
	@Override
	public void createCart(ShoppingCart cart) {
		shoppingCartMapper.insertCart(cart);
	}
	
	@Override
	public ShoppingCart getCartById(Integer cartId) {
		return shoppingCartMapper.getCartById(cartId);
	}
	
	@Override
	public void updateCart(ShoppingCart cart) {
		shoppingCartMapper.updateCart(cart);
	}
	
	
	@Override
	public List<CartItem> getCartItemsByCartId(Integer cartId) {
		return null;
	}
	
	@Override
	@Transactional
	public void addItemToCart(Integer cartId, CartItem cartItem) {
		// 添加项目到购物篮
		cartItem.setCartId(cartId);
		cartItemService.addCartItem(cartItem);
		
		// 更新购物车的总价和总重量
		ShoppingCart cart = getCartById(cartId);
		cart.setTotalPrice(cart.getTotalPrice() + cartItem.getQuantity() * cartItem.getPrice());
		cart.setTotalWeight(cart.getTotalWeight() + cartItem.getQuantity() * cartItem.getProductWeight());
		updateCart(cart);
	}
	
	@Override
	@Transactional
	public void removeItemFromCart(Integer cartId, Integer itemId) {
		// 获取要删除的购物车项目
		CartItem cartItem = cartItemService.getCartItemById(itemId);
		
		// 删除项目
		cartItemService.deleteCartItem(itemId);
		
		// 更新购物车的总价和总重量
		ShoppingCart cart = getCartById(cartId);
		cart.setTotalPrice(cart.getTotalPrice() - cartItem.getQuantity() * cartItem.getPrice());
		cart.setTotalWeight(cart.getTotalWeight() - cartItem.getQuantity() * cartItem.getProductWeight());
		updateCart(cart);
	}
	
	@Override
	public void clearCart(Integer cartId){
		// 清空购物车的项目和重置购物车的总价和总重量
		cartItemService.deleteCartItemsByCartId(cartId);
		ShoppingCart cart = getCartById(cartId);
		cart.setTotalPrice(0.0);
		cart.setTotalWeight(0.0);
		updateCart(cart);
	}
	
	@Override
	@Transactional
	public void deleteCart(Integer cartId) {
		cartItemService.deleteCartItemsByCartId(cartId);
		shoppingCartMapper.deleteCart(cartId);
	}
	
}
