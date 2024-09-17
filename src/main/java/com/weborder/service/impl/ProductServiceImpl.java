package com.weborder.service.impl;

import com.weborder.dao.ProductMapper;
import com.weborder.entity.Product;
import com.weborder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper productMapper;
	
	@Autowired
	public ProductServiceImpl(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	
	@Override
	public void createProduct(Product product) {
		productMapper.insertProduct(product);
	}
	
	@Override
	public Product getProductById(Integer productId) {
		return productMapper.getProductById(productId);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return productMapper.getAllProducts();
	}
	
	@Override
	public List<Product> getProductsByPage(int pageNumber, int pageSize) {
		int offset = (pageNumber - 1) * pageSize;
		return productMapper.getProductsByPage(pageSize, offset);
	}
	
	@Override
	public void updateProduct(Product product) {
		productMapper.updateProduct(product);
	}
	
	@Override
	public void deleteProduct(Integer productId) {
		productMapper.deleteProduct(productId);
	}
	
	@Override
	@Transactional
	public void reduceProductStock(Integer productId, Integer quantity) {
		Product product = productMapper.getProductById(productId);
		if (product != null && product.getStock() >= quantity) {
			product.setStock(product.getStock() - quantity);
			productMapper.updateProduct(product);
		} else {
			throw new RuntimeException("库存不足或产品不存在");
		}
	}
	
	@Override
	@Transactional
	public void increaseProductStock(Integer productId, Integer quantity) {
		Product product = productMapper.getProductById(productId);
		if (product != null) {
			product.setStock(product.getStock() + quantity);
			productMapper.updateProduct(product);
		} else {
			throw new RuntimeException("产品不存在");
		}
	}
}
