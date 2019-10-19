package com.cdyykj.system.commons;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 精确数学计算
 *
 * @author gonglb
 * @date 2017年2月28日 上午10:27:17
 */
public class NumberUtils {

	/**
	 * double 相加 HALF_EVEN
	 * 
	 * @param scale
	 * @param dd
	 * @return
	 */
	public static double doubleAdd(int scale, double... dd) {
		BigDecimal result = BigDecimal.ZERO;
		for (double n : dd) {
			result = result.add(new BigDecimal(Double.toString(n)));
		}
		return result.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	public static BigDecimal double2BigDecimal(int scale, double dd) {
		BigDecimal result = BigDecimal.ZERO;
		result = result.add(new BigDecimal(Double.toString(dd)));
		return result.setScale(scale, RoundingMode.HALF_EVEN);
	}

	/**
	 * double 相加
	 * 
	 * @param dd
	 * @return
	 */
	public static double doubleAdd(Double... dd) {
		BigDecimal result = BigDecimal.ZERO;
		for (Double n : dd) {
			if(null == n) {
				continue;
			}
			result = result.add(new BigDecimal(n.toString()));
		}
		return result.doubleValue();
	}

	public static double parseString2Double(String str, int scale) {
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal value = new BigDecimal(str);
		result = result.add(value);
		return result.setScale(scale).doubleValue();
	}

	public static double doubleAbs(double d, int scale) {
		BigDecimal result = BigDecimal.ZERO;
		if (d < 0.0) {
			d = -d;
		}
		result = result.add(new BigDecimal(d));
		return result.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	/**
	 * double 相加
	 * 
	 * @param scale
	 * @param mode
	 * @param dd
	 * @return
	 */
	public static double doubleAdd(int scale, RoundingMode mode, double... dd) {
		BigDecimal result = BigDecimal.ZERO;
		for (double n : dd) {
			result = result.add(new BigDecimal(Double.toString(n)));
		}
		return result.setScale(scale, mode).doubleValue();
	}

	/**
	 * double相加
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double doubleAdd(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();

	}

	/**
	 * 相加 取位 d1+d2 n.scale HALF_EVEN
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double doubleAdd(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).setScale(scale, RoundingMode.HALF_EVEN).doubleValue();

	}

	public static double add(BigDecimal bd1, BigDecimal bd2, int scale) {
		return bd1.add(bd2).setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	/**
	 * 相加 取位 d1+d2 n.scale
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @param mode
	 * @return
	 */
	public static double doubleAdd(double d1, double d2, int scale, RoundingMode mode) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).setScale(scale, mode).doubleValue();

	}

	/**
	 * double 相减
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double doubleSubtract(double d1, double d2) {
		return new BigDecimal(Double.toString(d1)).subtract(new BigDecimal(Double.toString(d2))).doubleValue();
	}

	/**
	 * double 相减
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double doubleSubtract(double d1, double d2, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(d1)).subtract(new BigDecimal(Double.toString(d2)));
		return b.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	/**
	 * double 相减
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @param mode
	 * @return
	 */
	public static double doubleSubtract(double d1, double d2, int scale, RoundingMode mode) {
		BigDecimal b = new BigDecimal(Double.toString(d1)).subtract(new BigDecimal(Double.toString(d2)));
		return b.setScale(scale, mode).doubleValue();
	}

	public static double doubleSubtract(double d1, int scale, double... d2) {
		BigDecimal b = new BigDecimal(Double.toString(d1));
		for (int i = 0; i < d2.length; i++) {
			double d = d2[i];
			b.subtract(new BigDecimal(d));
		}
		return b.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	/**
	 * 格式化数字
	 * 
	 * @param d1
	 * @param scale
	 * @return
	 */
	public static double formatNubmer(double d1, int scale) {
		BigDecimal bd = new BigDecimal(Double.toString(d1));
		return bd.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	/**
	 * 格式化数字 含取舍条件
	 * 
	 * @param d
	 * @param mode
	 * @param d1
	 * @return
	 */
	public static double formatNubmer(double d1, int scale, RoundingMode mode) {
		BigDecimal bd = new BigDecimal(Double.toString(d1));
		return bd.setScale(scale, mode).doubleValue();
	}

	public static BigDecimal formatNubmer(BigDecimal d1, int scale, RoundingMode mode) {
		return d1.setScale(scale, mode);
	}

	/**
	 * 格式化数字
	 * 
	 * @param d1
	 * @param scale
	 * @param formatter
	 * @return
	 */
	public static String formatNubmer(double d1, int scale, String formatter) {
		BigDecimal bd = new BigDecimal(Double.toString(d1));
		double dd = bd.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
		DecimalFormat df = new DecimalFormat(formatter);
		return df.format(dd);
	}

	/**
	 * 格式化数字
	 * 
	 * @param d1
	 * @param scale
	 * @param mode
	 * @param formatter
	 * @return
	 */
	public static String formatNubmer(double d1, int scale, RoundingMode mode, String formatter) {
		BigDecimal bd = new BigDecimal(Double.toString(d1));
		double dd = bd.setScale(scale, mode).doubleValue();
		DecimalFormat df = new DecimalFormat(formatter);
		return df.format(dd);
	}

	/**
	 * 货币形式格式字符串
	 * 
	 * @param #,##0.00
	 * @param value
	 */
	public static String formatNumberWithString(String formatter, String value) {
		if (StringUtils.isEmpty(value) || "null".equals(value)) {
			return "0.00";
		}
		if (StringUtils.isEmpty(formatter)) {
			formatter = "#,##0.00#";
		}
		BigDecimal bd = new BigDecimal(value);
		DecimalFormat df = new DecimalFormat(formatter);
		String result = df.format(bd);
		return result;
	}

	/**
	 * 相除
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double doubleDivide(double d1, double d2) {
		if(d1 == 0 || d2 == 0){
			return 0;
		}
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		
		return bd1.divide(bd2,2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 相除 取位 d1/d2 n.scale HALF_EVEN
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double doubleDivide(double d1, double d2, int scale) {
		if(d1 == 0 || d2 == 0){
			return 0;
		}
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		BigDecimal bd3 = bd1.divide(bd2, 10, RoundingMode.HALF_EVEN);
		return bd3.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	
	/**
	 * 相除 取位 d1/d2 n.scale
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @param mode
	 * @return
	 */
	public static double doubleDivide(double d1, double d2, int scale, RoundingMode mode) {
		if(d1 == 0 || d2 == 0){
			return 0;
		}
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		BigDecimal bd3 = bd1.divide(bd2, 10, mode);
		return bd3.setScale(scale, mode).doubleValue();
	}

	/**
	 * 相乘
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double doubleMultiply(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}

	/**
	 * 相乘 取位 d1*d2 n.scale HALF_EVEN
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double doubleMultiply(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	public static long double2Long(double d) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d));
		return bd1.setScale(0, RoundingMode.HALF_EVEN).longValue();
	}

	/**
	 * 相乘 取位 d1*d2 n.scale
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @param mode
	 * @return
	 */
	public static double doubleMultiply(double d1, double d2, int scale, RoundingMode mode) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).setScale(scale, mode).doubleValue();
	}

	/**
	 * 将整数转化为四位数字
	 * 
	 * @param number
	 * @return
	 */
	public static String formatFourNumber(int number) {
		NumberFormat nf = new DecimalFormat("0000");
		return nf.format(number);
	}

	public static boolean isDivisible(double d1, double d2) {
		return d1 % d2 == 0.0 ? true : false;
	}

	/**
	 * 页面展示保留两位小数的精度数字
	 * 
	 * @param num
	 * @return
	 */
	public static String scaleNumber(double num) {
		BigDecimal decimal = new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP);
		return decimal.toString();
	}
	
	/**
	 * 四舍五入取整
	 * @author cym
	 * @date 2017年6月21日下午5:05:06
	 */
	public static String getIntValue(double num) {
		BigDecimal decimal = new BigDecimal(num).setScale(0, BigDecimal.ROUND_HALF_UP);
		return decimal.intValue() + "";
	}

	public static double doublePower(double num, int power, int scale) {
		BigDecimal decimal = new BigDecimal(num);
		decimal = decimal.pow(power);
		return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	// /**
	//  * 获取b1/b2的百分比
	//  *
	//  * @param b1
	//  * @param b2
	//  * @param mode
	//  * @return
	//  */
	// public static String getPercent(BigDecimal b1, BigDecimal b2, int scale, RoundingMode mode, boolean fixedScale) {
	// 	if (b1 == null || b2 == null || b2.compareTo(BigDecimal.ZERO) == 0)
	// 		return (scale > 0 ? "0." : "0") + StringUtils.rightPad("", scale, '0') + "%";
	// 	NumberFormat format = getFormat(scale, mode, fixedScale);
	// 	BigDecimal radio = b1.divide(b2, 10, mode);
	// 	return format.format(radio);
	// }

	// /**
	//  * 获取b1/b2的百分比
	//  *
	//  * @param b1
	//  * @param b2
	//  * @param mode
	//  * @return
	//  */
	// public static String getPercent(double b1, double b2, int scale, RoundingMode mode, boolean fixedScale) {
	// 	return getPercent(org.apache.commons.lang3.math.NumberUtils.createBigDecimal(String.valueOf(b1)),
	// 			org.apache.commons.lang3.math.NumberUtils.createBigDecimal(String.valueOf(b2)), scale, mode,
	// 			fixedScale);
	// }
    //
	// public static String getPercent(BigDecimal b1, BigDecimal b2) {
	// 	return getPercent(b1, b2, 2, RoundingMode.HALF_UP, true);
	// }

	// public static String getPercent(double b1, double b2) {
	// 	return getPercent(b1, b2, 2, RoundingMode.HALF_UP, true);
	// }

	// public static String getLavePercent(String... p) {
	// 	return getLavePercent(2, RoundingMode.HALF_UP, true, p);
	// }

	// /**
	//  * 获取剩余百分数
	//  *
	//  * @param scale
	//  * @param mode
	//  * @param fixedScale
	//  * @param p
	//  * @return
	//  */
	// public static String getLavePercent(int scale, RoundingMode mode, boolean fixedScale, String... p) {
	// 	List<BigDecimal> aList = new ArrayList<BigDecimal>();
	// 	for (String s : p) {
	// 		if (s.contains("%")) {
	// 			aList.add(new BigDecimal(s.replaceAll("%", "")).movePointLeft(2));
	// 		} else {
	// 			aList.add(new BigDecimal(s));
	// 		}
	// 	}
	// 	BigDecimal lave = BigDecimal.ONE;
	// 	for (BigDecimal b : aList) {
	// 		lave = lave.subtract(b);
	// 	}
	// 	return getPercent(lave, BigDecimal.ONE, scale, mode, fixedScale);
	// }

	public static NumberFormat getFormat(int scale, RoundingMode mode, boolean fixedScale) {
		NumberFormat format = DecimalFormat.getPercentInstance();
		format.setMaximumFractionDigits(scale);
		if (fixedScale) {
			format.setMinimumFractionDigits(scale);
		}
		format.setRoundingMode(mode);
		return format;
	}

	public static NumberFormat getFormat() {
		return getFormat(2, RoundingMode.HALF_UP, true);
	}

	/**
	 * 将数字转化为“#,##0.00”格式
	 * 
	 * @param number
	 * @return
	 */
	public static String formatNumber(double number) {
		NumberFormat nf = new DecimalFormat("#,##0.00");
		return nf.format(number);
	}

	/**
	 * 格式数据,比如：123.00==>123 123.10==>123.1 123.11==>123.11
	 * 
	 * @param value
	 * @return
	 */
	public static String formatLastZero(double value) {
		String index_s = String.valueOf(value);
		if (index_s.substring(index_s.indexOf(".") + 1, index_s.length()).length() == 1) {
			index_s = index_s + "0";
		}
		String r_str = "";
		String str = index_s.substring(0, index_s.indexOf("."));
		String index_one = index_s.substring(index_s.indexOf(".") + 1, index_s.indexOf(".") + 2);
		String index_two = index_s.substring(index_s.indexOf(".") + 2, index_s.indexOf(".") + 3);
		if (index_two.equals("0")) {
			if (index_one.equals("0")) {
				r_str = str;
			} else {
				r_str = str + "." + index_one;
			}
		} else if (!index_two.equals("0")) {
			r_str = index_s;
		}
		return r_str;
	}

	/**
	 * 保留n位小数,舍弃多余位数	 *
	 *
	 * @author cym
	 * @date 2017年4月27日 下午7:00:48
	 */
	public static double formatDouble(double d, int n) {
		BigDecimal bg = new BigDecimal(d).setScale(n, BigDecimal.ROUND_HALF_UP);
		return bg.doubleValue();
		
	}

	/**
	 * 计算退货价格
	 * @param orderDetail
	 * @return
	 */
	public static Double computeRefundPrice(Double finalPrice, Integer allCount, Integer returnCount) {
		// 最终价格除以数量计算单价再乘以退货数量,保留两位小数得到退款金额
		return NumberUtils.formatDouble(NumberUtils.doubleMultiply(NumberUtils.doubleDivide(finalPrice, allCount), returnCount), 2);
	}

}
