package com.weborder.config;

import com.weborder.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> {
					response.sendRedirect("/api/users/login");
				})
				.and()
				.authorizeRequests()
				.antMatchers("/api/users/register", "/api/users/login","/swagger-ui/index.html","/v3/api-docs").permitAll() // 允许注册和登录的请求
				.anyRequest().authenticated() // 其他所有请求都需要认证
				.and()
				.addFilter(new JwtAuthenticationFilter(authenticationManager())); // 添加JWT认证过滤器
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
