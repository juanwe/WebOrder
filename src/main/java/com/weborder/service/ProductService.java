package com.weborder.service;

import com.weborder.entity.Product;

import java.util.List;
public interface ProductService {
	
	
	// 创建新产品
	void createProduct(Product product);
	
	// 根据ID获取产品
	Product getProductById(Integer productId);
	
	// 获取所有产品
	List<Product> getAllProducts();
	
	// 更新产品信息
	void updateProduct(Product product);
	
	// 删除产品
	void deleteProduct(Integer productId);
	
	// 减少产品库存
	void reduceProductStock(Integer productId, Integer quantity);
	
	// 增加产品库存
	void increaseProductStock(Integer productId, Integer quantity);
	
}
