package com.weborder.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Review {
	private Integer reviewId;      // 评价ID
	private Integer productId;     // 产品ID（外键）
	private Integer userId;        // 用户ID（外键）
	private Integer rating;        // 评价星级
	private String comment;        // 评价内容
	private Date reviewDate;       // 评价日期
}
