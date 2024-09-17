package com.weborder.controller;

import com.weborder.entity.CartItem;
import com.weborder.entity.ShoppingCart;
import com.weborder.service.CartItemService;
import com.weborder.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShoppingCartController.class)
class ShoppingCartControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShoppingCartService shoppingCartService;
	
	@MockBean
	private CartItemService cartItemService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	// 测试创建新购物车
	@Test
	@WithMockUser
	void testCreateCart() throws Exception {
		doNothing().when(shoppingCartService).createCart(any(ShoppingCart.class));
		
		mockMvc.perform(post("/api/carts")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"userId\": 1, \"totalPrice\": 100.00, \"totalWeight\": 10.00 }"))
				.andExpect(status().isOk());
	}
	
	// 测试获取购物车详情
	@Test
	@WithMockUser
	void testGetCartById() throws Exception {
		ShoppingCart cart = new ShoppingCart();
		cart.setCartId(1);
		cart.setUserId(1);
		cart.setTotalPrice(100.00);
		cart.setTotalWeight(10.00);
		
		when(shoppingCartService.getCartById(anyInt())).thenReturn(cart);
		
		mockMvc.perform(get("/api/carts/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cartId").value(1))
				.andExpect(jsonPath("$.userId").value(1))
				.andExpect(jsonPath("$.totalPrice").value(100.00))
				.andExpect(jsonPath("$.totalWeight").value(10.00));
	}
	
	// 测试获取购物车中的所有项目
	@Test
	@WithMockUser
	void testGetCartItemsByCartId() throws Exception {
		CartItem item1 = new CartItem();
		item1.setItemId(1);
		item1.setCartId(1);
		item1.setProductId(1);
		item1.setQuantity(2);
		item1.setPrice(20.00);
		item1.setProductWeight(5.00);
		
		CartItem item2 = new CartItem();
		item2.setItemId(2);
		item2.setCartId(1);
		item2.setProductId(2);
		item2.setQuantity(1);
		item2.setPrice(50.00);
		item2.setProductWeight(10.00);
		
		List<CartItem> items = Arrays.asList(item1, item2);
		
		when(cartItemService.getCartItemsByCartId(anyInt())).thenReturn(items);
		
		mockMvc.perform(get("/api/carts/1/items"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].itemId").value(1))
				.andExpect(jsonPath("$[0].productId").value(1))
				.andExpect(jsonPath("$[0].quantity").value(2))
				.andExpect(jsonPath("$[0].price").value(20.00))
				.andExpect(jsonPath("$[1].itemId").value(2))
				.andExpect(jsonPath("$[1].productId").value(2))
				.andExpect(jsonPath("$[1].quantity").value(1))
				.andExpect(jsonPath("$[1].price").value(50.00));
	}
	
	// 测试更新购物车
	@Test
	@WithMockUser
	void testUpdateCart() throws Exception {
		doNothing().when(shoppingCartService).updateCart(any(ShoppingCart.class));
		
		mockMvc.perform(put("/api/carts/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"totalPrice\": 200.00, \"totalWeight\": 20.00 }"))
				.andExpect(status().isOk());
	}
	
	// 测试清空购物车
	@Test
	@WithMockUser
	void testClearCart() throws Exception {
		doNothing().when(shoppingCartService).clearCart(anyInt());
		
		mockMvc.perform(delete("/api/carts/1/clear"))
				.andExpect(status().isOk());
	}
}
