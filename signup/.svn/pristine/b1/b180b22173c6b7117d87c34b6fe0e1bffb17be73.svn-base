package com.coolgua.signup.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coolgua.signup.interceptor.AntiInjectInterceptor;
import com.coolgua.signup.interceptor.LoginInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.coolgua.signup.controller", "com.coolgua.common.controller"})
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public AntiInjectInterceptor antiInjectInterceptor() {
		return new AntiInjectInterceptor();
	}

	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 防注入拦截器
		registry.addInterceptor(antiInjectInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/client/**")
			.excludePathPatterns("", "", "");
	}

}
