package com.weborder.controller;

import com.weborder.entity.Product;
import com.weborder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	// 创建新产品
	@PostMapping
	public ResponseEntity<Void> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return ResponseEntity.ok().build();
	}
	
	// 获取所有产品
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
	
	// 根据ID获取产品详情
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
		Product product = productService.getProductById(productId);
		return ResponseEntity.ok(product);
	}
	
	// 更新产品信息
	@PutMapping("/{productId}")
	public ResponseEntity<Void> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
		product.setProductId(productId);
		productService.updateProduct(product);
		return ResponseEntity.ok().build();
	}
	
	// 删除产品
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.ok().build();
	}
	
	// 减少产品库存
	@PutMapping("/{productId}/reduce-stock")
	public ResponseEntity<Void> reduceProductStock(@PathVariable Integer productId, @RequestParam Integer quantity) {
		productService.reduceProductStock(productId, quantity);
		return ResponseEntity.ok().build();
	}
	
	// 增加产品库存
	@PutMapping("/{productId}/increase-stock")
	public ResponseEntity<Void> increaseProductStock(@PathVariable Integer productId, @RequestParam Integer quantity) {
		productService.increaseProductStock(productId, quantity);
		return ResponseEntity.ok().build();
	}
}
