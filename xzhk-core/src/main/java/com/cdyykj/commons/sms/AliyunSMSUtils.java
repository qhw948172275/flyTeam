package com.cdyykj.commons.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.cdyykj.system.commons.JsonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 直播短信发送服务
 * 
 * @author chenbiao
 * @date 2017年6月29日 下午3:04:04
 *
 */
public class AliyunSMSUtils extends AliyunSMS {
	static Logger log = LoggerFactory.getLogger(AliyunSMSUtils.class);
	/**
	 * 签名
	 */
	static final String SIGN_NAME = "果子酱";
	
	public static final Long SMS_SEND_EXPIRE = 60*1000l; //外卖业务短信发送间隔有效期为1分钟
	
	/**
	 * 绑定手机号发送验证码
	 * 
	* @author chenbiao
	* @date 2018年5月31日 上午10:37:13
	* 参数说明 
	* 
	* @param phoneNumber
	* @param code
	* @return
	 */
	public static SendSmsResponse sendByBindPhone(String phoneNumber, String code) {
		try {
			log.info("_______telPhone is {} and code is {}",phoneNumber,code);
			ObjectNode json = JsonUtils.getMapperInstance().createObjectNode();
			json.put("code", code);
			return sendSms(SIGN_NAME, phoneNumber, "SMS_167020105", JsonUtils.beanToJson(json), null);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		SendSmsResponse response = sendByBindPhone("17602933086", "123456");
		System.out.println(response.getMessage());
		System.out.println(response.getBizId());
		System.out.println(response.getCode());
	}

}
