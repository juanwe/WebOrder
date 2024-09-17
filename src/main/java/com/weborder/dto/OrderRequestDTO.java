package com.weborder.dto;

import com.weborder.entity.Order;
import com.weborder.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
	private Order order;
	private List<OrderItem> orderItems;
}
