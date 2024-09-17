package com.weborder.controller;

import com.weborder.entity.Order;
import com.weborder.entity.OrderItem;
import com.weborder.service.OrderService;
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
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService orderService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	// 测试提交订单
	@Test
	@WithMockUser
	void testPlaceOrder() throws Exception {
		doNothing().when(orderService).placeOrder(any(Order.class), any(List.class));
		
		mockMvc.perform(post("/api/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"order\": { \"userId\": 1, \"totalPrice\": 200.00, \"shippingMethod\": \"Standard\", \"totalWeight\": 20.00 }, \"orderItems\": [{ \"productId\": 1, \"quantity\": 2, \"price\": 50.00 }] }"))
				.andExpect(status().isOk());
	}
	
	
	// 测试根据ID获取订单信息
	@Test
	@WithMockUser
	void testGetOrderById() throws Exception {
		Order order = new Order();
		order.setOrderId(1);
		order.setUserId(1);
		order.setTotalPrice(200.00);
		order.setOrderDate(new Date());
		order.setShippingMethod("Standard");
		order.setTotalWeight(20.00);
		
		when(orderService.getOrderById(anyInt())).thenReturn(order);
		
		mockMvc.perform(get("/api/orders/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId").value(1))
				.andExpect(jsonPath("$.userId").value(1))
				.andExpect(jsonPath("$.totalPrice").value(200.00))
				.andExpect(jsonPath("$.shippingMethod").value("Standard"))
				.andExpect(jsonPath("$.totalWeight").value(20.00));
	}
	
	// 测试获取用户的订单历史
	@Test
	@WithMockUser
	void testGetOrderHistoryByUserId() throws Exception {
		Order order1 = new Order();
		order1.setOrderId(1);
		order1.setUserId(1);
		order1.setTotalPrice(200.00);
		order1.setOrderDate(new Date());
		order1.setShippingMethod("Standard");
		order1.setTotalWeight(20.00);
		
		Order order2 = new Order();
		order2.setOrderId(2);
		order2.setUserId(1);
		order2.setTotalPrice(300.00);
		order2.setOrderDate(new Date());
		order2.setShippingMethod("Express");
		order2.setTotalWeight(30.00);
		
		List<Order> orders = Arrays.asList(order1, order2);
		
		when(orderService.getOrderHistoryByUserId(anyInt())).thenReturn(orders);
		
		mockMvc.perform(get("/api/orders/user/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].orderId").value(1))
				.andExpect(jsonPath("$[0].totalPrice").value(200.00))
				.andExpect(jsonPath("$[0].shippingMethod").value("Standard"))
				.andExpect(jsonPath("$[1].orderId").value(2))
				.andExpect(jsonPath("$[1].totalPrice").value(300.00))
				.andExpect(jsonPath("$[1].shippingMethod").value("Express"));
	}
}
