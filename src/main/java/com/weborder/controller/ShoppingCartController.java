package com.weborder.controller;

import com.weborder.entity.ShoppingCart;
import com.weborder.entity.CartItem;
import com.weborder.service.ShoppingCartService;
import com.weborder.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class ShoppingCartController {
	
	private final ShoppingCartService shoppingCartService;
	private final CartItemService cartItemService;
	
	@Autowired
	public ShoppingCartController(ShoppingCartService shoppingCartService, CartItemService cartItemService) {
		this.shoppingCartService = shoppingCartService;
		this.cartItemService = cartItemService;
	}
	
	// 创建新购物车
	@PostMapping
	public ResponseEntity<Void> createCart(@RequestBody ShoppingCart cart) {
		shoppingCartService.createCart(cart);
		return ResponseEntity.ok().build();
	}
	
	// 获取购物车详情
	@GetMapping("/{cartId}")
	public ResponseEntity<ShoppingCart> getCartById(@PathVariable Integer cartId) {
		ShoppingCart cart = shoppingCartService.getCartById(cartId);
		return ResponseEntity.ok(cart);
	}
	
	// 获取购物车中的所有项目
	@GetMapping("/{cartId}/items")
	public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable Integer cartId) {
		List<CartItem> cartItems = cartItemService.getCartItemsByCartId(cartId);
		return ResponseEntity.ok(cartItems);
	}
	
	// 更新购物车
	@PutMapping("/{cartId}")
	public ResponseEntity<Void> updateCart(@PathVariable Integer cartId, @RequestBody ShoppingCart cart) {
		cart.setCartId(cartId);
		shoppingCartService.updateCart(cart);
		return ResponseEntity.ok().build();
	}
	
	// 清空购物车
	@DeleteMapping("/{cartId}/clear")
	public ResponseEntity<Void> clearCart(@PathVariable Integer cartId) {
		shoppingCartService.clearCart(cartId);
		return ResponseEntity.ok().build();
	}
	
	// 删除购物车
	@DeleteMapping("/{cartId}/delete")
	public void deleteCart(@PathVariable Integer cartId){
		shoppingCartService.deleteCart(cartId);
	}
}
