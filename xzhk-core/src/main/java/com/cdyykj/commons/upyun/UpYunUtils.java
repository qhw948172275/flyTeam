package com.cdyykj.commons.upyun;

import com.cdyykj.system.commons.CalendarUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** 
* 又拍云帮助类
* @author BILL CHEN
* @date 2016年5月18日 下午5:46:53 
*  
*/
public class UpYunUtils {
	
	
	static final String limit = "/";

	static Logger logger = LoggerFactory.getLogger(UpYunUtils.class);

	/**
	 * @Description: 通过文件对象上传
	 * @author BILL CHEN
	 * @date 2015年11月30日 下午4:41:23 参数说明
	 * 
	 * @param file
	 * @param path
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @see
	 */
	public static synchronized String uploadFile(final File file, String path, String fileName) throws IOException {
		String tempFolder = CalendarUtils.dateToString(Calendar.getInstance().getTime(), "yyyy/MM/dd") + limit;
		path = path +tempFolder;
		String buffix = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString()+buffix;
		Map<String,String> map = new HashMap<String, String>();
		map.put("x-gmkerl-thumb", "/rotate/90");
		UpYun.getInstance().writeFile(path+fileName, file, true,map);
		return tempFolder+fileName; 
	}
	
	/**
	 * @Description: 通过文件对象上传
	 * @author BILL CHEN
	 * @date 2015年11月30日 下午4:41:23 参数说明
	 * 
	 * @param input
	 * @param path
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @see
	 */
	public static synchronized String uploadFile(final InputStream input, String path, String fileName) throws IOException {
		String tempFolder = CalendarUtils.dateToString(Calendar.getInstance().getTime(), "yyyy/MM/dd") + limit;
		String buffix = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString()+buffix;
		UpYun.getInstance().writeFile(path+tempFolder+fileName, input, true);
		return tempFolder+fileName; 
	}
	/**
	 * 图片旋转操作
	* @author chenbiao
	* @date 2017年1月3日 下午6:27:19
	* 参数说明 
	* 
	* @param visitPath
	* @param baseFilePath
	* @param newFilePath
	* @param rotate
	* @return
	* @throws IOException
	 */
	public static synchronized String ratateImg(String visitPath, final String baseFilePath, String uploadPath, String newFilePath, Integer rotate) throws IOException {
		byte[] bytes = readFile(visitPath+baseFilePath);
		InputStream stream = new ByteArrayInputStream(bytes);
		Map<String,String> map = new HashMap<String, String>();
		map.put("x-gmkerl-thumb", "/rotate/"+rotate);
		UpYun.getInstance().writeFile(uploadPath+newFilePath, stream, true,map);
		return newFilePath; 
	}
	/**
	 * 通过url远程下载图片并上传又拍云
	* @author chenbiao
	* @date 2017年1月6日 上午10:23:24
	* 参数说明 
	* 
	* @param url
	* @param uploadPath
	* @return
	* @throws IOException
	 */
	public static synchronized String uploadFileByUrl(String url, String uploadPath) throws IOException {
		try {
			byte[] bytes = readFile(url);
			InputStream stream = new ByteArrayInputStream(bytes);
			String tempFolder = CalendarUtils.dateToString(Calendar.getInstance().getTime(), "yyyy/MM/dd") + limit;
			url = url.substring(url.lastIndexOf("/"));
			String fileName = null;
			if(url.lastIndexOf(".") > 0){				
				fileName = UUID.randomUUID().toString() + url.substring(url.lastIndexOf("."));
			}else
				fileName = UUID.randomUUID().toString() + ".jpg";
			UpYun.getInstance().writeFile(uploadPath +tempFolder + fileName, stream, true);
			return tempFolder+fileName; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 下载并上传用户头像
	* @author chenbiao
	* @date 2017年3月20日 下午8:03:21
	* 参数说明 
	* 
	* @param url
	* @param uploadPath
	* @return
	 */
	public static String uploadUserPic(String url, String uploadPath){
		try{
			String buffix = ".jpg";
			byte[] bytes = readFile(url);
			url = url.substring(url.lastIndexOf("/"));
			String fileName = null;
			if(url.lastIndexOf(".") > 0){				
				fileName = UUID.randomUUID().toString() + url.substring(url.lastIndexOf("."));
			}else
				fileName = UUID.randomUUID().toString() + buffix;
			InputStream stream = new ByteArrayInputStream(bytes);
			fileName= uploadFile(stream,uploadPath,fileName);
			return fileName;
		}catch(Exception e){
				e.printStackTrace();	
		}
		return null;
	}
	
	/**
	 * 远程读取文件
	* @author chenbiao
	* @date 2017年1月3日 下午7:31:26
	* 参数说明 
	* 
	* @param fileUrl
	* @return
	 */
	public static byte[] readFile(String fileUrl) {
		try {
	        URL url = new URL(fileUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(30 * 1000);
	        InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[10240];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, len);
	        }
	        inStream.close();
	        return outStream.toByteArray();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}


	/**
	 * 远程读取文件
	 * @author chenbiao
	 * @date 2017年1月3日 下午7:31:26
	 * 参数说明
	 *
	 * @param fileUrl
	 * @return
	 */
	public static InputStream readFileInStream(String fileUrl) {
		try {
			URL url = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(30 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			return inStream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 
	* @Description: 浏览器form上传
	* @author BILL CHEN
	* @date 2015年11月30日 下午5:18:46
	* 参数说明 
	* 
	* @param file 文件上传对象
	* @param path 存储目录
	* @return  
	* @see
	*/
	public static synchronized String uploadFile(final CommonsMultipartFile file, String path) {
		return uploadFtpFile(file, path);
	}
	
	/** 
	* @Description: 浏览器form上传,通过MultipartHttpServletRequest方式获取MultipartFile文件对象进行上传操作
	* @author BILL CHEN
	* @date 2016年3月3日 下午5:22:25
	* 参数说明 
	* 
	* @param file
	* @param path
	* @return  
	*/
	public static synchronized String uploadFtpFile(final MultipartFile file, String path) {
		String tempFolder = CalendarUtils.dateToString(Calendar.getInstance().getTime(), "yyyy/MM/dd") + limit;
		path = path +tempFolder;
		String fileName = file.getOriginalFilename();
		if(fileName.contains(".")){
			String buffix = fileName.substring(fileName.lastIndexOf("."));
			fileName = UUID.randomUUID().toString() + buffix;
		} else {
			fileName = UUID.randomUUID().toString() + ".jpg";
		}
		UpYun.getInstance().writeFile(path+limit+fileName, file, true);
		return tempFolder+fileName; 
	}
	/** 
	 * @Description: 浏览器form上传,通过MultipartHttpServletRequest方式获取MultipartFile文件对象进行上传操作
	 * @author BILL CHEN
	 * @date 2016年3月3日 下午5:22:25
	 * 参数说明 
	 * 
	 * @param file
	 * @param path
	 * @return  
	 */
	public static synchronized String uploadFtpFile(final MultipartFile file, String path, String fileName) {
		String tempFolder = CalendarUtils.dateToString(Calendar.getInstance().getTime(), "yyyy/MM/dd") + limit;
		path = path +tempFolder;
		UpYun.getInstance().writeFile(path+limit+fileName, file, true);
		return tempFolder+fileName; 
	}

	/**
	 * 删除ftp上的插件
	 * 
	 * @param path
	 *
	 * @param fileName
	 *            插件名
	 */
	public static void deleteFile(String path, String fileName) {

		UpYun upYun = UpYun.getInstance();
		if (upYun.deleteFile(path + limit + fileName)) {
			logger.debug("删除成功");
		} else {
			logger.debug("删除失败");
		}
	}
}
