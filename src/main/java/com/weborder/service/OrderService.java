package com.weborder.service;

import com.weborder.entity.Order;
import com.weborder.entity.OrderItem;

import java.util.List;

public interface OrderService {
	
	// 创建新订单
	void createOrder(Order order);
	
	// 根据ID获取订单
	Order getOrderById(Integer orderId);
	
	// 获取所有订单
	List<Order> getAllOrders();
	
	// 提交订单（包括订单和订单项的创建及库存更新）
	void placeOrder(Order order, List<OrderItem> orderItems);
}
