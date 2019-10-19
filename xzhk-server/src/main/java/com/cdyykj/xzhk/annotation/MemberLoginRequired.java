package com.cdyykj.xzhk.annotation;

import java.lang.annotation.*;

/**
 * 登录拦截
* @author chenbiao
* @date 2018年5月30日 下午8:43:08 
*
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberLoginRequired {

}
