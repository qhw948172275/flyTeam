package com.cdyykj.system.commons.result;

/**
 * json返回,标准构造类
 * 工具方法 :cn.hlhdj.duoji.commons.JsonResultUtils
 *
 * @author cym
 * @date 2017年2月22日 下午5:31:50
 */
public class JsonResult {
	
	public String result; // 成功:"ok", 失败:"fail"
	public Object object; // 返回json内容
	public String errorCode; // 错误码
	public String errorMsg; // 错误信息

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
