package com.cdyykj.commons.utils;


import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;

/**
 * 所有错误码功能
* @author chenbiao
* @date 2018年5月31日 上午9:21:54 
*
 */
public enum ErrorCodeUtils {
	

	/**
	 * 手机号或密码错误
	 */
	ERROR_DEVICE_ID_REPEAT(1015,"设备登录帐号数超过限制"),
	
	/**
	 * refreshToken不存在
	 */
	ERROR_REFRESH_TOKEN_NOT_EXISTS(1014,"refreshToken不存在"),
	
	/**
	 * 密码错误
	 */
	ERROR_PASSWORD(1013,"密码错误"),
	
	/**
	 * token不存在
	 */
	ERROR_TOKEN_NOT_EXISTS(1012,"token不存在"),
	/**
	 * 账号不存在或者已删除
	 */
	ERROR_ACCOUNT_NOT_EXISTS_OR_DELETE(1011,"当前账号不存在或已删除"),

	/**
	 * 注销失败
	 */
	ERROR_LOGOUT_FAIL(1010,"注销失败"),
	/**
	 * 用户名不存在
	 */
	ERROR_USERNAME_NOT_EXISTS(1009,"用户名不存在"),
	/**
	 * 手机号不存在
	 */
	ERROR_PHONE_NOT_EXISTS(1007,"手机号不存在"),
	
	/**
	 * 手机号或密码错误
	 */
	ERROR_PHONE_OR_PASSWORD_ERROR(1006,"手机号或密码错误"),
	/**
	 * 用户名或密码错误
	 */
	ERROR_USERNAME_OR_PASSWORD_ERROR(1005,"用户名或密码错误"),
	/**
	 * 登录已失效
	 */
	ERROR_LOGIN_TIMEOUT(1004,"登陆已失效"),

	
	/**
	 * 还未登录
	 */
	ERROR_NO_LOGIN(1003,"您还未登录"),
	
	/**
	 * 手机号格式不正确
	 */
	ERROR_PHONE_VALIDATE_ERROR(1002,"手机号格式错误"),
	 
	/**
	 * 验证码错误
	 */
	ERROR_CAPTCHA_CODE(1001,"验证码错误"),
	/**
	 * 404
	 */
	ERROR_404(404,"访问地址出错"),
	/**
	 * 500
	 */
	ERROR_500(500,"服务器网络异常"),
	/**
	 * 404
	 */
	ERROR_400(400,"访问参数错误"),

	/**
	 * 改店铺已禁用 4115
	 */
	ERROR_4115(4115,"店铺已禁用"),

	/**
	 * 登录失败
	 */
	ERROR_4116(4116,"登录失败");
	
	ErrorCodeUtils(int code, String message){
		result = JsonResultUtils.buildJsonFail(String.valueOf(code), message);
	}
	private JsonResult result;

	public JsonResult getResult() {
		return result;
	}
	
}
