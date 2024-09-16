package com.weborder.service;

import com.weborder.entity.Review;

import java.util.List;

public interface ReviewService {
	
	// 创建新评价
	void createReview(Review review);
	
	// 根据产品ID获取所有评价
	List<Review> getReviewsByProductId(Integer productId);
}
