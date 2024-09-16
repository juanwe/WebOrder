package com.weborder.entity;

import lombok.Data;

@Data
public class Product {
	private Integer productId;     // 产品ID
	private String productName;    // 产品名称
	private Double price;          // 单价
	private String description;    // 商品描述
	private Double shipping;       // 运费
	private Double weight;         // 重量
	private String image;          // 商品图片存放位置
	private Integer stock;         // 库存数量
}
