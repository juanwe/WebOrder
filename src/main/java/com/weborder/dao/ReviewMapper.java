package com.weborder.dao;

import com.weborder.entity.Review;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {
	
	// 插入评价
	@Insert("INSERT INTO Review(ProductID, UserID, Rating, Comment, ReviewDate) VALUES(#{productId}, #{userId}, #{rating}, #{comment}, #{reviewDate})")
	@Options(useGeneratedKeys = true, keyProperty = "reviewId")
	void insertReview(Review review);
	
	// 根据产品ID查询评价
	@Select("SELECT * FROM Review WHERE ProductID = #{productId}")
	List<Review> getReviewsByProductId(Integer productId);
	
	//根据用户ID和产品ID获取用户对产品的评价
	@Select("SELECT * FROM review where UserID = #{userId} and ProductID= #{productId}")
	Review getUserReviewForProduct(Integer userId, Integer productId);
}
