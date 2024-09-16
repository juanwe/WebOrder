package com.weborder.entity;

import lombok.Data;

@Data
public class CartItem {
	private Integer itemId;        // 项目ID
	private Integer cartId;        // 购物篮ID（外键）
	private Integer productId;     // 产品ID（外键）
	private Integer quantity;      // 数量
}
