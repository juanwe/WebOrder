package com.weborder.service.impl;

import com.weborder.dao.ShoppingCartMapper;
import com.weborder.entity.ShoppingCart;
import com.weborder.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	private final ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper) {
		this.shoppingCartMapper = shoppingCartMapper;
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
	public void deleteCart(Integer cartId) {
		shoppingCartMapper.deleteCart(cartId);
	}
	
	@Override
	public void clearCart(Integer cartId){
		shoppingCartMapper
	}
}
