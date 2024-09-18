package com.weborder.controller;

import com.weborder.entity.Review;
import com.weborder.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	// 添加评价
	@PostMapping
	public ResponseEntity<String> createReview(@RequestBody Review review) {
		// 检查用户是否已经评价过该产品
		boolean hasReviewed = reviewService.hasUserReviewedProduct(review.getUserId(), review.getProductId());
		
		if (hasReviewed) {
			// 如果用户已经评价过该产品，返回相应的错误信息
			return ResponseEntity.status(400).body("You have already reviewed this product");
		}
		
		// 如果没有评价过，则添加评价
		reviewService.createReview(review);
		return ResponseEntity.ok("Review created successfully");
	}
	
	// 获取产品的所有评价
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Integer productId) {
		List<Review> reviews = reviewService.getReviewsByProductId(productId);
		return ResponseEntity.ok(reviews);
	}
	
	// 获取用户对某个产品的评价
	@GetMapping("/product/{productId}/user/{userId}")
	public ResponseEntity<Review> getUserReviewForProduct(@PathVariable Integer productId, @PathVariable Integer userId) {
		Review review = reviewService.getUserReviewForProduct(userId, productId);
		return ResponseEntity.ok(review);
	}
}
