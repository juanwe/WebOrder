package com.weborder.service;

import com.weborder.entity.Review;

import java.util.List;

public interface ReviewService {
	
	boolean hasUserReviewedProduct(Integer userId, Integer productId);
	
	/**
	 * 创建新的评价
	 *
	 * @param review 要创建的评价信息
	 */
	void createReview(Review review);
	
	/**
	 * 根据产品ID获取所有评价
	 *
	 * @param productId 产品ID
	 * @return 返回产品的评价列表
	 */
	List<Review> getReviewsByProductId(Integer productId);
	
	/**
	 * 根据用户ID和产品ID获取用户对产品的评价
	 *
	 * @param userId 用户ID
	 * @param productId 产品ID
	 * @return 返回用户对该产品的评价
	 */
	Review getUserReviewForProduct(Integer userId, Integer productId);
}
