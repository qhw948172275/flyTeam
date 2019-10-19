package com.cdyykj.system.commons.result;

/**
 * 返回标准json格式,工具方法
 *
 * @author cym
 * @date 2017年2月22日 下午5:32:57
 */
public class JsonResultUtils {
	public static final String OK = "ok";
	public static final String FAIL = "fail";


	/**
	 * 返回失败
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:14
	 */
	public static JsonResult buildJsonFail() {
		return buildJson(FAIL, null, null, null);
	}

	/**
	 * 返回失败,带object
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:22
	 */
	public static JsonResult buildJsonFail(Object object) {
		return buildJson(FAIL, object, null, null);
	}

	/**
	 * 返回失败,带object,errorCode
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:22
	 */
	public static JsonResult buildJsonFail(Object object, String errorCode) {
		return buildJson(FAIL, object, errorCode, null);
	}

	/**
	 * 返回失败,带object,errorCode,errorMsg
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:22
	 */
	public static JsonResult buildJsonFail(Object object, String errorCode, String errorMsg) {
		return buildJson(FAIL, object, errorCode, errorMsg);
	}

	/**
	 * 返回失败,带errorCode
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:22
	 */
	public static JsonResult buildJsonFailCode(String errorCode) {
		return buildJson(FAIL, null, errorCode, null);
	}
	
	/**
	 * 返回失败,带errorCode
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:22
	 */
	public static JsonResult buildJsonFailMsg(String errorMsg) {
		return buildJson(FAIL, null, null, errorMsg);
	}
	
	
	/**
	 * 返回失败,带errorCode,errorMsg
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:22
	 */
	public static JsonResult buildJsonFail(String errorCode, String errorMsg) {
		return buildJson(FAIL, null, errorCode, errorMsg);
	}
	
	
	/**
	 * 返回成功
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:34
	 */
	public static JsonResult buildJsonOK() {
		return buildJson(OK, null, null, null);
	}

	/**
	 * 返回成功带Object
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:41
	 */
	public static JsonResult buildJsonOK(Object object) {
		return buildJson(OK, object, null, null);
	}

	/**
	 * 返回成功自定义状态码 信息
	 * @param code
	 * @param msg
	 * @return
	 */
	public static JsonResult buildJsonOK(String code, String msg) {
		return buildJson(OK, null, code, msg);
	}

	/**
	 * 返回自定义结果
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:51
	 */
	public static JsonResult buildJson(String result) {
		return buildJson(result, null, null, null);
	}

	/**
	 * 返回自定义结果,带Object
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:33:57
	 */
	public static JsonResult buildJson(String result, Object object) {
		return buildJson(result, object, null, null);
	}

	/**
	 * 放回自定义结果,带Object,errorCode
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:34:12
	 */
	public static JsonResult buildJson(String result, Object object, String errorCode) {
		return buildJson(result, object, errorCode, null);
	}

	/**
	 * 放回自定义结果,带Object,errorCode,errorMsg
	 *
	 * @author cym
	 * @date 2017年2月22日 下午5:34:12
	 */
	public static JsonResult buildJson(String result, Object object, String errorCode, String errorMsg) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(result);
		jsonResult.setObject(object);
		jsonResult.setErrorCode(errorCode);
		jsonResult.setErrorMsg(errorMsg);

		return jsonResult;
	}
}
