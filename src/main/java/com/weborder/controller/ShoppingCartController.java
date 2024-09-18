package com.weborder.controller;

import com.weborder.entity.ShoppingCart;
import com.weborder.entity.CartItem;
import com.weborder.service.ShoppingCartService;
import com.weborder.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
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
	public ResponseEntity<Integer> createCart(@RequestBody ShoppingCart cart) {
		shoppingCartService.createCart(cart);
		return ResponseEntity.ok(cart.getCartId());  // 返回创建的购物车ID
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
	
	// 增加项目到购物车
	@PutMapping("/{cartId}/add")
	public ResponseEntity<Void> addItemToCart(@PathVariable Integer cartId,@RequestBody CartItem cartItem){
		shoppingCartService.addItemToCart(cartId,cartItem);
		return ResponseEntity.ok().build();
	}
	
	// 在购物车中删除项目
	@PutMapping("/{cartId}/remove/{itemId}")
	public ResponseEntity<Void> removeItemToCart(@PathVariable Integer cartId, @PathVariable Integer itemId){
		shoppingCartService.removeItemFromCart(cartId,itemId);
		return ResponseEntity.ok().build();
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
