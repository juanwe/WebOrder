package com.weborder.controller;

import com.weborder.entity.Order;
import com.weborder.entity.OrderItem;
import com.weborder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// 提交订单
	@PostMapping
	public ResponseEntity<Void> placeOrder(@RequestBody Order order, @RequestBody List<OrderItem> orderItems) {
		orderService.placeOrder(order, orderItems);
		return ResponseEntity.ok().build();
	}
	
	// 根据ID获取订单
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
		Order order = orderService.getOrderById(orderId);
		return ResponseEntity.ok(order);
	}
	
	// 获取用户的订单历史
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getOrderHistoryByUserId(@PathVariable Integer userId) {
		List<Order> orders = orderService.getOrderHistoryByUserId(userId);
		return ResponseEntity.ok(orders);
	}
}
