package com.weborder.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	
	private final String secretKey = "SecretKeyToGenJWTs"; // JWT密钥
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 从请求头中获取JWT Token
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		
		// 解析Token
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			// 解析Token
			Claims claims = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token.replace("Bearer ", ""))
					.getBody();
			
			// 获取用户名
			String user = claims.getSubject();
			
			if (user != null) {
				// 返回认证信息
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
			return null;
		}
		return null;
	}
}
