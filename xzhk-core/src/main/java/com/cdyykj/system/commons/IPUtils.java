package com.cdyykj.system.commons;

import com.fasterxml.jackson.databind.JsonNode;


/**
 * 根据IP地址解析出所对应的地理位置信息
 * 
* @author chenbiao
* @date 2018年8月23日 下午3:14:07 
*
 */
public class IPUtils {
	
	private static final String AK = "DMVDeyv2xqSU4CVrZA0F0vp4wVjbaQcY";
	
	private static final String REQUEST_URL = "http://api.map.baidu.com/location/ip";
	
	private static final String COOR = "BD09ll";//百度坐标，如果需要地图使用，则需要通过请求返回坐标地理位置
	
	/**
	 * 构建请求地址
	* @author chenbiao
	* @date 2018年8月23日 下午2:59:51
	* 参数说明 
	* 
	* @param ip
	* @return
	 */
	public static String buildRequestUrl(String ip) {
		StringBuffer sb = new StringBuffer(REQUEST_URL);
		sb.append("?ak=").append(AK)
		.append("&ip=").append(ip)
		.append("&coor=").append(COOR);
		return sb.toString();
	}
	/**
	 * 获取地理位置信息
	* @author chenbiao
	* @date 2018年8月23日 下午3:06:22
	* 参数说明 
	* 
	* @param ip
	* @return
	 */
	public static JsonNode getIPInfo(String ip) {
		return HTTPUtils.httpGet(buildRequestUrl(ip));
	}
	/**
	 * 获取省份信息
	* @author chenbiao
	* @date 2018年8月23日 下午3:13:51
	* 参数说明 
	* 
	* @param ipInfo
	* @return
	 */
	public static String getProvince(JsonNode ipInfo) {
		try {
			if(ipInfo.has("content")) {
				JsonNode content = ipInfo.get("content");
				if(content.has("address_detail")) {
					return content.get("address_detail").get("province").asText();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取详细地址
	* @author chenbiao
	* @date 2018年8月23日 下午3:13:10
	* 参数说明 
	* 
	* @param ipInfo
	* @return
	 */
	public static String getAddress(JsonNode ipInfo) {
		try {
			if(ipInfo.has("content")) {
				return ipInfo.get("content").get("address").asText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取坐标信息（百度坐标）
	* @author chenbiao
	* @date 2018年8月23日 下午3:14:46
	* 参数说明 
	* 
	* @param ipInfo
	* @return Long型数组，下标0表示经度；下标1表示纬度
	 */
	public static Long[] getPoint(JsonNode ipInfo) {
		try {
			if(ipInfo.has("content")) {
				JsonNode point = ipInfo.get("content").get("point");
				return new Long[]{point.get("x").asLong(),point.get("y").asLong()};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getIPInfo("182.150.27.185"));
	}

}
