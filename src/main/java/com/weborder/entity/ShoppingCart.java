package com.weborder.entity;

import lombok.Data;

@Data
public class ShoppingCart {
	private Integer cartId;        // 购物篮ID
	private Integer userId;        // 用户ID（外键）
	private Double totalPrice;     // 总价
	private Double totalWeight;    // 总重量
}
