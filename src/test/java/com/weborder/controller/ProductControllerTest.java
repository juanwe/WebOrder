package com.weborder.controller;

import com.weborder.entity.Product;
import com.weborder.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	// 测试创建新产品
	@Test
	@WithMockUser
	void testCreateProduct() throws Exception {
		doNothing().when(productService).createProduct(any(Product.class));
		
		mockMvc.perform(post("/api/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"productName\": \"testProduct\", \"price\": 10.99 }"))
				.andExpect(status().isOk());
	}
	
	// 测试根据ID获取产品
	@Test
	@WithMockUser
	void testGetProductById() throws Exception {
		Product product = new Product();
		product.setProductId(1);
		product.setProductName("testProduct");
		product.setPrice(10.99);
		
		when(productService.getProductById(anyInt())).thenReturn(product);
		
		mockMvc.perform(get("/api/products/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").value(1))
				.andExpect(jsonPath("$.productName").value("testProduct"))
				.andExpect(jsonPath("$.price").value(10.99));
	}
	
	// 测试获取所有产品
	@Test
	@WithMockUser
	void testGetAllProducts() throws Exception {
		Product product1 = new Product();
		product1.setProductId(1);
		product1.setProductName("testProduct1");
		product1.setPrice(10.99);
		
		Product product2 = new Product();
		product2.setProductId(2);
		product2.setProductName("testProduct2");
		product2.setPrice(12.99);
		
		List<Product> products = Arrays.asList(product1, product2);
		
		when(productService.getAllProducts()).thenReturn(products);
		
		mockMvc.perform(get("/api/products"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].productId").value(1))
				.andExpect(jsonPath("$[0].productName").value("testProduct1"))
				.andExpect(jsonPath("$[1].productId").value(2))
				.andExpect(jsonPath("$[1].productName").value("testProduct2"));
	}
	
	// 测试分页获取产品
	@Test
	@WithMockUser
	void testGetProductsByPage() throws Exception {
		Product product1 = new Product();
		product1.setProductId(1);
		product1.setProductName("testProduct1");
		product1.setPrice(10.99);
		
		Product product2 = new Product();
		product2.setProductId(2);
		product2.setProductName("testProduct2");
		product2.setPrice(12.99);
		
		List<Product> products = Arrays.asList(product1, product2);
		
		when(productService.getProductsByPage(anyInt(), anyInt())).thenReturn(products);
		
		mockMvc.perform(get("/api/products/page")
						.param("pageNumber", "1")
						.param("pageSize", "2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].productId").value(1))
				.andExpect(jsonPath("$[0].productName").value("testProduct1"))
				.andExpect(jsonPath("$[1].productId").value(2))
				.andExpect(jsonPath("$[1].productName").value("testProduct2"));
	}
	
	// 测试更新产品信息
	@Test
	@WithMockUser
	void testUpdateProduct() throws Exception {
		doNothing().when(productService).updateProduct(any(Product.class));
		
		mockMvc.perform(put("/api/products/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"productName\": \"updatedProduct\", \"price\": 15.99 }"))
				.andExpect(status().isOk());
	}
	
	// 测试删除产品
	@Test
	@WithMockUser
	void testDeleteProduct() throws Exception {
		doNothing().when(productService).deleteProduct(anyInt());
		
		mockMvc.perform(delete("/api/products/1"))
				.andExpect(status().isOk());
	}
	
	// 测试减少产品库存
	@Test
	@WithMockUser
	void testReduceProductStock() throws Exception {
		doNothing().when(productService).reduceProductStock(anyInt(), anyInt());
		
		mockMvc.perform(put("/api/products/1/reduce-stock")
						.param("quantity", "10"))
				.andExpect(status().isOk());
	}
	
	// 测试增加产品库存
	@Test
	@WithMockUser
	void testIncreaseProductStock() throws Exception {
		doNothing().when(productService).increaseProductStock(anyInt(), anyInt());
		
		mockMvc.perform(put("/api/products/1/increase-stock")
						.param("quantity", "10"))
				.andExpect(status().isOk());
	}
}
