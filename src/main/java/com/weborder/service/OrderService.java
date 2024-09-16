package com.weborder.service;

import com.weborder.entity.Order;
import com.weborder.entity.OrderItem;

import java.util.List;

public interface OrderService {
	
	/**
	 * 创建新订单
	 *
	 * @param order 要创建的订单
	 */
	void createOrder(Order order);
	
	/**
	 * 根据订单ID获取订单信息
	 *
	 * @param orderId 订单ID
	 * @return 返回订单信息
	 */
	Order getOrderById(Integer orderId);
	
	/**
	 * 获取所有订单
	 *
	 * @return 返回所有订单列表
	 */
	List<Order> getAllOrders();
	
	/**
	 * 提交订单
	 *
	 * @param order 要提交的订单
	 * @param orderItems 订单中的项目列表
	 */
	void placeOrder(Order order, List<OrderItem> orderItems);
	
	/**
	 * 根据用户ID获取订单历史
	 *
	 * @param userId 用户ID
	 * @return 返回订单历史列表
	 */
	List<Order> getOrderHistoryByUserId(Integer userId);
}
