package com.weborder.service.impl;

import com.weborder.dao.CartItemMapper;
import com.weborder.entity.CartItem;
import com.weborder.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	private final CartItemMapper cartItemMapper;
	
	@Autowired
	public CartItemServiceImpl(CartItemMapper cartItemMapper) {
		this.cartItemMapper = cartItemMapper;
	}
	
	@Override
	public void addCartItem(CartItem cartItem) {
		cartItemMapper.insertCartItem(cartItem);
	}
	
	@Override
	public List<CartItem> getCartItemsByCartId(Integer cartId) {
		return cartItemMapper.getCartItemsByCartId(cartId);
	}
	
	@Override
	public void updateCartItem(CartItem cartItem) {
		cartItemMapper.updateCartItem(cartItem);
	}
	
	@Override
	public void deleteCartItem(Integer itemId) {
		cartItemMapper.deleteCartItem(itemId);
	}
}
