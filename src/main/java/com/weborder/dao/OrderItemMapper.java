package com.weborder.dao;

import com.weborder.entity.OrderItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderItemMapper {
	
	// 插入订单项目
	@Insert("INSERT INTO OrderItem(OrderID, ProductID, Quantity, Price) VALUES(#{orderId}, #{productId}, #{quantity}, #{price})")
	@Options(useGeneratedKeys = true, keyProperty = "itemId")
	void insertOrderItem(OrderItem orderItem);
	
	// 根据订单ID查询订单项目
	@Select("SELECT * FROM OrderItem WHERE OrderID = #{orderId}")
	List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
