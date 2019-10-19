package com.cdyykj.xzhk.annotation;

import com.alibaba.druid.util.StringUtils;
import com.cdyykj.commons.utils.ErrorCodeUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.RedisUtils;
import com.cdyykj.system.commons.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * 登录验证拦截
 * 
* @author chenbiao
* @date 2018年5月30日 下午8:45:38 
*
 */
public class MemberLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	RedisUtils redisUtils;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			MemberLoginRequired loginRequired = findAnnotation((HandlerMethod) handler, MemberLoginRequired.class);
			// 没有声明需要权限,或者声明不验证权限
			if (loginRequired == null) {
				return true;
			} else {
				String token = request.getHeader("token");
				if (StringUtils.isEmpty(token)) {
					token = request.getParameter("token");
				}

				System.out.println("token = " + token);

				// 在这里实现自己的权限验证逻辑
				if (StringUtils.isEmpty(token)) {// 如果验证成功返回true（这里直接写false来模拟验证失败的处理）
					response.setContentType("application/json;charset=utf-8");
					response.getWriter().write(JsonUtils.beanToJson(ErrorCodeUtils.ERROR_NO_LOGIN.getResult()));
					System.out.println("您还未登录");
					return false;
				} else if (!redisUtils.validateStringKeyExists(SystemConstants.MEMBER_TOKEN_KEY + token)) {//redis中是否存在key
					response.setContentType("application/json;charset=utf-8");
					response.getWriter().write(JsonUtils.beanToJson(ErrorCodeUtils.ERROR_LOGIN_TIMEOUT.getResult()));
					System.out.println("登陆已失效，请退出重新登录");
					return false;
				} else {
					//放行
					return true;
				}
			}
		} else {
			return true;
		}
	}

	private <T extends Annotation> T findAnnotation(HandlerMethod handler, Class<T> annotationType) {
		T annotation = handler.getBeanType().getAnnotation(annotationType);
		if (annotation != null)
			return annotation;
		return handler.getMethodAnnotation(annotationType);
	}

	public RedisUtils getRedisUtils() {
		return redisUtils;
	}

	public void setRedisUtils(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}


}
