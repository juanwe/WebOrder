package com.weborder.controller;

import com.weborder.dto.OrderRequestDTO;
import com.weborder.entity.Order;
import com.weborder.entity.OrderItem;
import com.weborder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
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
	public ResponseEntity<Void> placeOrder(@RequestBody OrderRequestDTO orderRequest) {
		orderService.placeOrder(orderRequest.getOrder(), orderRequest.getOrderItems());
		return ResponseEntity.ok().build();
	}
	
	// 根据ID获取订单
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
		Order order = orderService.getOrderById(orderId);
		return ResponseEntity.ok(order);
	}
	
	// 根据订单ID查询订单项
	@GetMapping("/details/{orderId}")
	public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Integer orderId){
		List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
		return ResponseEntity.ok(orderItems);
	}
	
	// 获取用户的订单历史
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getOrderHistoryByUserId(@PathVariable Integer userId) {
		List<Order> orders = orderService.getOrderHistoryByUserId(userId);
		return ResponseEntity.ok(orders);
	}
}
