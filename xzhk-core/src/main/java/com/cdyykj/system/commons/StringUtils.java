package com.cdyykj.system.commons;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	// 剪切String
	public static String cutString(int count, String str) {
		if (str.length() > count) {
			str = str.substring(0, count) + "...";
		}

		return str;
	}

	// 剪切String,一个中文算2个字符
	public static String limitString(int count, String str) {
		try {
			if (str == null) {
				return "";
			} else {
				int tempSubLength = count;//
				String subStr = str.substring(0, str.length() < count ? str.length() : count);
				int subByetsLength = subStr.getBytes("GBK").length;// 截取子串的字节长度

				// 说明截取的字符串中包含有汉字
				boolean isCut = false;
				while (subByetsLength > tempSubLength) {
					int subLengthTemp = --count;
					subStr = str.substring(0, (subLengthTemp > str.length() ? str.length() : subLengthTemp));
					subByetsLength = subStr.getBytes("GBK").length;
					isCut = true;
				}

				if (isCut) {
					return subStr + "...";
				} else {
					return subStr;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * Java文件操作 获取文件扩展�?
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/*
	 * Java文件操作 获取不带扩展名的文件�?
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	// 生成随机字符
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	// 生成随机数字
	public static String getRandomNum(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String changeToUTF8(String json) {
		try {
			String iso = new String(json.getBytes("UTF-8"), "ISO-8859-1");
			String utf8 = new String(iso.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println(utf8);
			return utf8;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String checkSql(String str) {
		if (str == null) {
			return null;
		}
		str = str.replaceAll(";", "");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("'", "");
		str = str.replaceAll("--", "");
		str = str.replaceAll("/", "");
		str = str.replaceAll("%", "");
		return str.trim();
	}

	public static boolean notUnLawFul(String content) {
		if (content.toLowerCase().contains("script") || content.toLowerCase().contains("http")) {
			return true;
		}

		return false;
	}

	public static String subComma(String str) {
		if (str.startsWith(",")) {
			return str.substring(1, str.length());
		} else {
			return str;
		}
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 给电话号码加星号
	 * 
	 * @author cym
	 * @date 2017年5月28日上午9:14:35
	 */
	public static String encodePhone(String phone) {
		if (phone == null) {
			return null;
		}

		char[] array = phone.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (i >= 3 && i <= 6) {
				array[i] = '*';
			}
		}

		return new String(array);
	}

	public static void main(String[] args) {
		System.out.println(formatNumberShort(10900l));
	}

	/**
	 * 超过1000,化为1K,超过10000,化为1W
	 * 
	 * @param point
	 * @return
	 */
	public static String formatNumberShort(Long point) {
		if (point == null) {
			return "0";
		}

		if (point >= 1000 && point < 10000) {
			if (point % 1000 < 100) {
				return point / 1000 + "k";
			} else {
				return NumberUtils.doubleDivide(point, 1000, 1) + "k";
			}
		}
		if (point >= 10000) {
			if (point % 10000 < 1000) {
				return point / 10000 + "W";
			} else {
				return NumberUtils.doubleDivide(point, 10000, 1) + "W";
			}
		}
		return String.valueOf(point);
	}

	public static String formatNumberShort(Integer point) {
		return formatNumberShort((long) point);
	}
	
	/**
	 * 移除特殊字符
	 *
	 *
	 * @author cym   
	 * @date 2018年2月8日 下午12:15:02
	 */
	public static String removeSpecial(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】'；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);

		return m.replaceAll("").trim();
	}


	public static String listToString(List<String> list){

		if(list==null){
			return null;
		}

		StringBuilder result = new StringBuilder();
		boolean first = true;

		//第一个前面不拼接","
		for(String string :list) {
			if(first) {
				first=false;
			}else{
				result.append(",");
			}
			result.append(string);
		}
		return result.toString();
	}
}
