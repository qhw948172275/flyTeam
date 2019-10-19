package com.cdyykj.xzhk.config;

import com.cdyykj.xzhk.annotation.MemberLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

/**
 * 添加拦截器
 * 
 * @author chenbiao
 * @date 2018年9月18日 下午10:03:58
 *
 */
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截匹配路径下的请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
		registry.addInterceptor(getMemberLoginInterceptor()).addPathPatterns("/wechat/member/**");
	}

	@Bean
	public HandlerInterceptor getMemberLoginInterceptor() {
		return new MemberLoginInterceptor();
	}


	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

	}

	/**
	 * 配置servlet处理
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
