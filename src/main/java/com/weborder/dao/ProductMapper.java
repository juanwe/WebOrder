package com.weborder.dao;

import com.weborder.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
	
	// 插入新产品
	@Insert("INSERT INTO Product(ProductName, Price, Description, Shipping, Weight, Image, Stock) VALUES(#{productName}, #{price}, #{description}, #{shipping}, #{weight}, #{image}, #{stock})")
	@Options(useGeneratedKeys = true, keyProperty = "productId")
	void insertProduct(Product product);
	
	// 根据ID查询产品
	@Select("SELECT * FROM Product WHERE ProductID = #{productId}")
	Product getProductById(Integer productId);
	
	// 查询所有产品
	@Select("SELECT * FROM Product")
	List<Product> getAllProducts();
	
	// 查询分页的产品列表
	@Select("SELECT * FROM Product LIMIT #{pageSize} OFFSET #{offset}")
	List<Product> getProductsByPage(@Param("pageSize") int pageSize, @Param("offset") int offset);
	
	
	// 更新产品信息
	@Update("UPDATE Product SET ProductName = #{productName}, Price = #{price}, Description = #{description}, Shipping = #{shipping}, Weight = #{weight}, Image = #{image}, Stock = #{stock} WHERE ProductID = #{productId}")
	void updateProduct(Product product);
	
	// 删除产品
	@Delete("DELETE FROM Product WHERE ProductID = #{productId}")
	void deleteProduct(Integer productId);
}
