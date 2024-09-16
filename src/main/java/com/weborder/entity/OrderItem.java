package com.weborder.entity;

import lombok.Data;

@Data
public class OrderItem {
	private Integer itemId;        // 项目ID
	private Integer orderId;       // 订单ID（外键）
	private Integer productId;     // 产品ID（外键）
	private Integer quantity;      // 数量
	private Double price;          // 单价
}
