package com.weborder.controller;

import com.weborder.entity.Review;
import com.weborder.service.ReviewService;
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
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReviewService reviewService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	// 测试创建评价
	@Test
	@WithMockUser
	void testCreateReview() throws Exception {
		doNothing().when(reviewService).createReview(any(Review.class));
		
		mockMvc.perform(post("/api/reviews")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"productId\": 1, \"userId\": 1, \"rating\": 5, \"comment\": \"Great product!\" }"))
				.andExpect(status().isOk());
	}
	
	// 测试根据产品ID获取所有评价
	@Test
	@WithMockUser
	void testGetReviewsByProductId() throws Exception {
		Review review1 = new Review();
		review1.setReviewId(1);
		review1.setProductId(1);
		review1.setUserId(1);
		review1.setRating(5);
		review1.setComment("Great product!");
		review1.setReviewDate(new Date());
		
		Review review2 = new Review();
		review2.setReviewId(2);
		review2.setProductId(1);
		review2.setUserId(2);
		review2.setRating(4);
		review2.setComment("Good product.");
		review2.setReviewDate(new Date());
		
		List<Review> reviews = Arrays.asList(review1, review2);
		
		when(reviewService.getReviewsByProductId(anyInt())).thenReturn(reviews);
		
		mockMvc.perform(get("/api/reviews/product/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].reviewId").value(1))
				.andExpect(jsonPath("$[0].comment").value("Great product!"))
				.andExpect(jsonPath("$[1].reviewId").value(2))
				.andExpect(jsonPath("$[1].comment").value("Good product."));
	}
	
	// 测试根据用户ID和产品ID获取用户对产品的评价
	@Test
	@WithMockUser
	void testGetUserReviewForProduct() throws Exception {
		Review review = new Review();
		review.setReviewId(1);
		review.setProductId(1);
		review.setUserId(1);
		review.setRating(5);
		review.setComment("Amazing product!");
		review.setReviewDate(new Date());
		
		when(reviewService.getUserReviewForProduct(anyInt(), anyInt())).thenReturn(review);
		
		mockMvc.perform(get("/api/reviews/product/1/user/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.reviewId").value(1))
				.andExpect(jsonPath("$.comment").value("Amazing product!"))
				.andExpect(jsonPath("$.rating").value(5));
	}
}
