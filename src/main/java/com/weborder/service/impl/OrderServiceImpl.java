package com.weborder.service.impl;

import com.weborder.dao.OrderItemMapper;
import com.weborder.dao.OrderMapper;
import com.weborder.dao.ProductMapper;
import com.weborder.entity.Order;
import com.weborder.entity.OrderItem;
import com.weborder.service.OrderService;
import com.weborder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderMapper orderMapper;
	private final OrderItemMapper orderItemMapper;
	private final ProductService productService;
	
	@Autowired
	public OrderServiceImpl(OrderMapper orderMapper, OrderItemMapper orderItemMapper, ProductService productService) {
		this.orderMapper = orderMapper;
		this.orderItemMapper = orderItemMapper;
		this.productService = productService;
	}
	
	@Override
	public void createOrder(Order order) {
		orderMapper.insertOrder(order);
	}
	
	@Override
	public Order getOrderById(Integer orderId) {
		return orderMapper.getOrderById(orderId);
	}
	
	@Override
	public List<Order> getAllOrders() {
		return orderMapper.getAllOrders();
	}
	
	@Override
	@Transactional
	public void placeOrder(Order order, List<OrderItem> orderItems) {
		// 创建订单
		orderMapper.insertOrder(order);
		
		// 创建订单项并更新库存
		for (OrderItem item : orderItems) {
			item.setOrderId(order.getOrderId());
			orderItemMapper.insertOrderItem(item);
			// 减少产品库存
			productService.reduceProductStock(item.getProductId(), item.getQuantity());
		}
	}
	
	@Override
	public List<Order> getOrderHistoryByUserId(Integer userId) {
		return orderMapper.getOrdersByUserId(userId);
	}
}
