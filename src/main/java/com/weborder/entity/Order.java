package com.weborder.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
	private Integer orderId;       // 订单ID
	private Integer userId;        // 用户ID（外键）
	private Date orderDate;        // 订单日期
	private Double totalPrice;     // 总价
	private String shippingMethod; // 运输方式
	private Double totalWeight;    // 总重量
}
