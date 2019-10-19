package com.cdyykj.system.commons;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HTTPUtils {

	private static Logger log = LoggerFactory.getLogger(HTTPUtils.class);

	public static String sendGetHttpRequest(String getUrl, Map<String, Object> parameters) {
		String lines = "";
		BufferedReader in = null;
		try {
			String parameStr = "";
			if (parameters != null && !parameters.isEmpty()) {
				for (String parameName : parameters.keySet())
					parameStr += parameName + "=" + parameters.get(parameName) + "&";
				getUrl = getUrl + "?" + parameStr;
			}
			URL url = new URL(getUrl);
			HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();// 打开连接
			openConnection.setRequestProperty("Accept", "*/*");
			openConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)"); // 模拟ie浏览器
			openConnection.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
			String value;
			while ((value = in.readLine()) != null)
				lines += value;
			in.close();
			// 断开连接
			openConnection.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines.equals("") ? null : lines;
	}

	public static String getGet(String getUrl, Map<String, String> parameters) {
		String lines = "";
		BufferedReader in = null;
		try {
			String parameStr = "";
			if (parameters != null && !parameters.isEmpty()) {
				for (String parameName : parameters.keySet())
					parameStr += parameName + "=" + parameters.get(parameName) + "&";
				getUrl = getUrl + "?" + parameStr;
			}
			URL url = new URL(getUrl);
			HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();// 打开连接
			openConnection.setRequestProperty("Accept", "*/*");
			openConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)"); // 模拟ie浏览器
			openConnection.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
			String value;
			while ((value = in.readLine()) != null)
				lines += value;
			in.close();
			// 断开连接
			openConnection.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines.equals("") ? null : lines;
	}

	public static String sendGetHttpRequest(String getUrl) {
		String lines = "";
		BufferedReader in = null;
		try {
			URL url = new URL(getUrl);
			HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();// 打开连接
			openConnection.setRequestMethod("GET");// 默认请求方式
			openConnection.setRequestProperty("Accept", "*/*");
			openConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)"); // 模拟ie浏览器
			in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
			String value;
			while ((value = in.readLine()) != null)
				lines += value;
			in.close();
			// 断开连接
			openConnection.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines.equals("") ? null : lines;
	}

	public static <T> String sendPostHttpRequest(String getUrl, Map<String, T> parameters) {
		PrintWriter out = null;
		BufferedReader in = null;
		String lines = "";
		try {
			String parameStr = "";
			if (parameters != null && !parameters.isEmpty()) {
				for (String parameName : parameters.keySet())
					parameStr += parameName + "=" + parameters.get(parameName) + "&";
				parameStr = parameStr.substring(0, parameStr.length() - 1);
			}
			URL url = new URL(getUrl);
			HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();// 打开连接
			openConnection.setRequestProperty("Accept", "*/*");
			openConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)"); // 模拟ie浏览器
			openConnection.setRequestMethod("POST");
			System.out.println(parameStr);
			// 发送POST请求必须设置如下两行
			// 打开读写
			openConnection.setDoOutput(true);
			openConnection.setDoInput(true);
			// 设置连接类型
			openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// openConnection.connect();//开始连接
			// openConnection.getInputStream()隐含openConnection.connect()
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(openConnection.getOutputStream());
			if (!parameStr.equals(""))
				// 发送请求参数
				out.print(parameStr);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null)
				lines += line;
			// 断开连接
			openConnection.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines.equals("") ? null : lines;
	}

	public static String sendPostHttpRequest(String getUrl) {
		return sendPostHttpRequest(getUrl, "");
	}

	public static String sendPostHttpRequest(String getUrl, String parameStr) {
		PrintWriter out = null;
		BufferedReader in = null;
		String lines = "";
		try {
			URL url = new URL(getUrl);
			HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();// 打开连接
			openConnection.setRequestProperty("Accept", "*/*");
			openConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)"); // 模拟ie浏览器
			openConnection.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			// 打开读写
			openConnection.setDoOutput(true);
			openConnection.setDoInput(true);
			// 设置连接类型
			openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// openConnection.connect();//开始连接
			// openConnection.getInputStream()隐含openConnection.connect()
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(openConnection.getOutputStream());
			// 发送请求参数
			if (parameStr != null && !parameStr.equals(""))
				out.print(parameStr);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null)
				lines += line;
			// 断开连接
			openConnection.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines.equals("") ? null : lines;
	}

	/**
	 * 发送post请求
	 * @param strURL 请求路径
	 * @param params 请求参数
	 * @return
	 */
	public static String doPost(String strURL, String params){
		System.out.println(strURL);
		System.out.println(params);
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("contentType", "utf-8");
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "utf-8"); // utf-8编码
//				System.out.println(result);
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param responseMsg
	 *            要回送的消息
	 *            状态码 正常：200 异常：400
	 */
	public static void doPost(HttpServletRequest request, HttpServletResponse response, String responseMsg) {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");// HTTP 1.1
			response.setHeader("Pragma", "no-cache");// HTTP 1.0
			response.setDateHeader("Expires", 0);

			PrintWriter writer = response.getWriter();
			writer.write(responseMsg);
			writer.flush();
			writer.close();

		} catch (Exception ex) {
			log.error("Error Message------>" + ex.getMessage());
		}
	}

	public static JsonNode httpGet(String url) {
		try {
			return JsonUtils.stringToJson(sendPostHttpRequest(url));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}