package com.weborder.service;

import com.weborder.entity.Product;

import java.util.List;

public interface ProductService {
	
	/**
	 * 创建新产品
	 *
	 * @param product 要创建的产品信息
	 */
	void createProduct(Product product);
	
	/**
	 * 根据产品ID获取产品信息
	 *
	 * @param productId 产品ID
	 * @return 返回产品信息
	 */
	Product getProductById(Integer productId);
	
	/**
	 * 获取所有产品
	 *
	 * @return 返回产品列表
	 */
	List<Product> getAllProducts();
	
	/**
	 * 更新产品信息
	 *
	 * @param product 要更新的产品信息
	 */
	void updateProduct(Product product);
	
	/**
	 * 删除产品
	 *
	 * @param productId 产品ID
	 */
	void deleteProduct(Integer productId);
	
	/**
	 * 减少产品库存
	 *
	 * @param productId 产品ID
	 * @param quantity  减少的数量
	 */
	void reduceProductStock(Integer productId, Integer quantity);
	
	/**
	 * 增加产品库存
	 *
	 * @param productId 产品ID
	 * @param quantity  增加的数量
	 */
	void increaseProductStock(Integer productId, Integer quantity);
}
