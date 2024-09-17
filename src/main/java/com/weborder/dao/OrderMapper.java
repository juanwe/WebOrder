package com.weborder.dao;

import com.weborder.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
	
	// 插入新订单
	@Insert("INSERT INTO `Order`(UserID, OrderDate, TotalPrice, ShippingMethod, TotalWeight) VALUES(#{userId}, #{orderDate}, #{totalPrice}, #{shippingMethod}, #{totalWeight})")
	@Options(useGeneratedKeys = true, keyProperty = "orderId")
	void insertOrder(Order order);
	
	// 根据ID查询订单
	@Select("SELECT * FROM `Order` WHERE OrderID = #{orderId}")
	Order getOrderById(Integer orderId);
	
	// 查询所有订单
	@Select("SELECT * FROM `Order`")
	List<Order> getAllOrders();
	
	// 根据用户ID查询订单
	@Select("SELECT * FROM `Order` WHERE UserID = #{userId}")
	List<Order> getOrdersByUserId(Integer userId);
}
