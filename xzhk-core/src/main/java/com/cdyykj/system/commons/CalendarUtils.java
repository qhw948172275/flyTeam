package com.cdyykj.system.commons;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间格式帮助类
 * 
 * @author BILL CHEN
 *
 */
public class CalendarUtils {

	/**
	 * 年月日时分秒没有间隔符
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:17:59
	 */
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String yyyyMMdd_HHmmss = "yyyyMMdd-HHmmss";

	public static final String yyyy = "yyyy";



	/**
	 * cron的时间格式
	 */
	public static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

	/**
	 * 年月日 按照“-”间隔
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:24
	 */
	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * 年月 按照“-”间隔
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:24
	 */
	public static final String yyyy_MM = "yyyy-MM";
	/**
	 * 转化时间
	 */
	public static final String yyyy_M = "yyyy-M";

	/**
	 * 月日 按照“-”间隔
	 * 
	 * @author kimi
	 * @dateTime 2012-6-26 下午5:42:23
	 */
	public static final String MM_dd = "MM-dd";

	/**
	 * 年月日 无分隔符
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:45
	 */
	public static final String yyyyMMdd = "yyyyMMdd";
	/**
	 * 年月 无分隔符
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:45
	 */
	public static final String yyyyMM = "yyyyMM";

	/**
	 * 时分秒 按“:”分隔
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:47
	 */
	public static final String HH_mm_ss = "HH:mm:ss";
	/**
	 * 时分，按“：”分隔
	 */
	public static final String HH_mm = "HH:mm";

	/**
	 * 年月日时分秒 年月日"-" 时"："
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:51
	 */
	public static final String yyyy_MM_dd__HH = "yyyy-MM-dd HH";

	/**
	 * 年月日时分秒 年月日"-" 时分"："
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:51
	 */
	public static final String yyyy_MM_dd__HH_mm = "yyyy-MM-dd HH:mm";

	/**
	 * 年月日时分秒 年月日"-" 时分秒"："
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:51
	 */
	public static final String yyyy_MM_dd__HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 年月日时分秒毫秒 年月日"-" 时分秒"：" 加上毫秒
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:55
	 */
	public static final String yyyy_MM_dd__HH_mm_ss__EE = "yyyy-MM-dd HH:mm:ss EE";

	/**
	 * 月日 时分
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:18:55
	 */
	public static final String MM_dd__HH_mm = "MM-dd HH:mm";
	/**
	 * 年/月/日
	 */
	public static final String yyyy__MM__dd = "yyyy/MM/dd";

	/**
	 * 毫秒格式
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:19:00
	 */
	public static final String EE = "EE";

	/**
	 * 年
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:19:03
	 */
	public static final String YEAR = "yyyy";

	/**
	 * 月
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:19:05
	 */
	public static final String MONTH = "MM";

	/**
	 * 日
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:19:08
	 */
	public static final String DAY = "dd";

	/**
	 * 时
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:19:10
	 */
	public static final String HOUR = "HH";

	/**
	 * 分
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:19:13
	 */
	public static final String MINUTE = "mm";

	/**
	 * 秒
	 * 
	 * @author kimi
	 * @dateTime 2011-11-2 下午3:19:15
	 */
	public static final String SECOND = "ss";

	/**
	 * @Fields yyyy_MM_dd_week : 年月日 周?
	 */
	public static final String yyyy_MM_dd_week = "";

	public static final String YESTER_DAY_TEXT = "昨天";

	public static final String mm月_dd日 = "MM月dd日";
	public static final String yyyy年_mm月_dd日 = "yyyy年MM月dd日";
	public static final String yyyy年_mm月_dd日_HH = "yyyy年MM月dd日 HH";
	public static final String yyyy年_mm月_dd日_HH_mm = "yyyy年MM月dd日 HH:mm";

	public static final String[] weeks = new String[] { "周日","周一", "周二", "周三", "周四", "周五","周六"};
	/**
	 * 东8区区时
	 */
	public static final String TIME_ZONE = "GMT+08:00";

	/**
	 * @Fields DAY_TO_LONG : 一天的时间转换成Long值
	 */
	public static final long DAY_TO_LONG = 24 * 60 * 60 * 1000L;

	/**
	 * 1小时转换为时间戳
	 */
	public static final long HOUR_TO_LONG = 60 * 60 * 1000L;

	/**
	 * 5分钟转换为时间戳
	 */
	public static final long FIVE_MIN_TO_LONG = 5 * 60 * 1000L;

	/**
	 * @Fields WEEK_TO_LONG : 一周时间转换成long值
	 */
	public static final long WEEK_TO_LONG = 7 * 23 * 59 * 59 * 1000L;

	/**
	 * 获取当前时间凌晨0分0秒0毫秒0微秒的时间
	 * 
	 * @param calendar
	 * @return
	 */
	public static Date getDayTime(Calendar calendar) {
		calendar.set(Calendar.MILLISECOND, 0);// 微秒
		calendar.set(Calendar.SECOND, 0);// 秒
		calendar.set(Calendar.MINUTE, 0);// 分
		calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
		return calendar.getTime();
	}

	/**
	 * 根据天数获取当前时间之前或者之后天数的凌晨时间
	 * 
	 * @author BILL CHEN
	 * @date 2015年7月21日 下午3:45:15 参数说明
	 * 
	 * @param time
	 * @return 例如1表示一天后的0点0分0秒0毫秒
	 * @see
	 */
	public static Date getDateByDays(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);// 计算N天前后后的时间
		c.set(Calendar.HOUR_OF_DAY, 0);// 设置小时归零
		c.set(Calendar.MINUTE, 0);// 设置分钟归零
		c.set(Calendar.SECOND, 0);// 设置秒归零
		c.set(Calendar.MILLISECOND, 0);// 设置毫秒归零
		return c.getTime();
	}

	/**
	 * 根据天数获取指定时间之前或者之后天数的凌晨时间
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月26日 下午1:16:31 参数说明
	 * 
	 * @param days
	 * @param date
	 * @return
	 */
	public static Date getDateByDays(int days, Date date) {
		Calendar c = getCalendar(date);
		c.add(Calendar.DATE, days);// 计算N天前后后的时间
		c.set(Calendar.HOUR_OF_DAY, 0);// 设置小时归零
		c.set(Calendar.MINUTE, 0);// 设置分钟归零
		c.set(Calendar.SECOND, 0);// 设置秒归零
		c.set(Calendar.MILLISECOND, 0);// 设置毫秒归零
		return c.getTime();
	}

	public static Date getDateByDays(int days, long timestamp) {
		Calendar c = getCalendar(timestamp);
		c.add(Calendar.DATE, days);// 计算N天前后后的时间
		c.set(Calendar.HOUR_OF_DAY, 0);// 设置小时归零
		c.set(Calendar.MINUTE, 0);// 设置分钟归零
		c.set(Calendar.SECOND, 0);// 设置秒归零
		c.set(Calendar.MILLISECOND, 0);// 设置毫秒归零
		return c.getTime();
	}

	/**
	 * 根据天数获取指定时间之前或者之后天数的当前小时时间
	 * 
	 * @author BILL CHEN
	 * @date 2016年6月7日 下午3:39:37 参数说明
	 * 
	 * @param days
	 * @return
	 */
	public static Date getDateAndHoursByDays(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);// 计算N天前后后的时间
		c.set(Calendar.MINUTE, 0);// 设置分钟归零
		c.set(Calendar.SECOND, 0);// 设置秒归零
		c.set(Calendar.MILLISECOND, 0);// 设置毫秒归零
		return c.getTime();
	}

	/**
	 * 根据时间操作对象自定义小时
	 * 
	 * @author chenbiao
	 * @date 2016年8月1日 下午8:00:23 参数说明
	 * 
	 * @param calendar
	 * @param hours
	 * @return
	 */
	public static Date getDateByCustomHours(Calendar calendar, int hours) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, hours);
		c.set(Calendar.MINUTE, 0);// 设置分钟归零
		c.set(Calendar.SECOND, 0);// 设置秒归零
		c.set(Calendar.MILLISECOND, 0);// 设置毫秒归零
		return c.getTime();
	}

	/**
	 * 
	 * @Title: getTheDay
	 * @Description: 根据天数获取当前时间之前或者之后天数的任意时间(这里用一句话描述这个方法的作用)
	 * @author liuxun
	 * @date 2015年10月30日 下午5:25:26 参数说明
	 * 
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @param milliseconds
	 * @return
	 */
	public static Long getTheDay(int days, int hours, int minutes, int seconds, int milliseconds) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);// 计算N天前后后的时间
		c.set(Calendar.HOUR_OF_DAY, hours);// 设置小时
		c.set(Calendar.MINUTE, minutes);// 设置分钟
		c.set(Calendar.SECOND, seconds);// 设置秒
		c.set(Calendar.MILLISECOND, milliseconds);// 设置毫秒
		return c.getTimeInMillis();
	}
	
	/**
	 * 
	 * 根据日期对象获取获取当月最后一天23点59分59秒999毫秒时间对象
	 * 
	 * @author wuu
	 * @date 2016年1月4日 下午6:17:26 参数说明
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonthByDate(Date date) {
		Calendar cal = getCalendar(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 根据日期对象获取获取当月第一天0点0分0秒0毫秒时间对象
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月26日 上午10:47:20 参数说明
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = getCalendar(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Calendar setMonth(Calendar cal, int month) {
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar addMonth(Calendar cal, int month) {
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar addHour(Calendar cal, int hour) {
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * 根据日期获取月份值
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月26日 上午10:42:59 参数说明
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		return getMonth(getCalendar(date));
	}

	public static int getMonth(Calendar cal) {
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getBetweensByTimestamp(Date a, Date b) {
		return getBetweensByTimestamp(a.getTime(), b.getTime(), false);
	}

	/**
	 * 计算两个时间戳之间相差的天数
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月31日 下午7:39:40 参数说明
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getBetweensByTimestamp(long a, long b) {
		return getBetweensByTimestamp(a, b, false);
	}

	/**
	 * 计算两个时间戳之间相差的天数
	 * 
	 * @author chenbiao
	 * @date 2016年9月18日 下午4:51:48 参数说明
	 * 
	 * @param a
	 * @param b
	 * @param flag
	 *            相同日期情况下，不同时分秒是否算一天。true表示算一天；false表示不算
	 * @return
	 */
	public static int getBetweensByTimestamp(long a, long b, boolean flag) {
		long days = a - b;
		if (days % DAY_TO_LONG == 0) {
			return (int) (days / DAY_TO_LONG);
		}
		if (flag) {
			return (int) ((days / DAY_TO_LONG) + 1);
		}
		return (int) (days / DAY_TO_LONG);
	}

	/**
	 * 计算两个时间相差天数
	 * 
	 * @author chenbiao
	 * @date 2019年3月10日 下午2:13:50 参数说明
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthSpace(Date date1, Date date2) {
		Calendar c1 = getCalendar(date1);
		Calendar c2 = getCalendar(date2);
		
		int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
		return month + result;

	}

	/**
	 * 获取当前的时间操作对象
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月19日 下午5:14:28 参数说明
	 * 
	 * @return
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @author chenbiao
	 * @date 2016年8月5日 下午1:59:48 参数说明
	 * 
	 * @return
	 */
	public static long getTimeStamp() {
		return Calendar.getInstance().getTimeInMillis();
	}

	/**
	 * 获取当前时间对象
	 * 
	 * @author chenbiao
	 * @date 2016年10月10日 上午11:04:54 参数说明
	 * 
	 * @return
	 */
	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 根据时间戳获取对应的时间操作对象
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月19日 下午5:14:10 参数说明
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static Calendar getCalendar(long timeStamp) {
		Calendar calendar = getCalendar();
		calendar.setTimeInMillis(timeStamp);
		return calendar;
	}

	/**
	 * 根据日期对象获取时间操作对象
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月26日 上午10:39:13 参数说明
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getCalendar(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 
	 * @Title: timeStamp2Date
	 * @Description:时间戳转换成日期格式
	 * @author wuu
	 * @date 2016年1月7日 上午11:09:57 参数说明
	 * 
	 * @param stamp
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String timeStampToString(String stamp, String format) {
		return timeStampToString(Long.parseLong(stamp), format);
	}

	public static String timeStampToString(Long stamp, String format) {
		if (null == stamp)
			return null;
		return new SimpleDateFormat(format).format(getCalendar(stamp).getTime());
	}

	/**
	 * 日期对象转时间字符串
	 * 
	 * @param calendar
	 * @return
	 */
	public static String calendarToString(Calendar calendar, String format) {
		return dateToString(calendar.getTime(), format);
	}

	/**
	 * 时间对象转字符串格式
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月19日 下午5:09:20 参数说明
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 字符串转换日期对象
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月19日 下午5:19:01 参数说明
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String dateStr, String format) {
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date stringToDate(String dateStr, String format, int hours, int minutes, int seconds, int mills) {
		Date date = stringToDate(dateStr, format);
		Calendar cal = getCalendar(date);
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.MILLISECOND, mills);
		return cal.getTime();
	}

	public static Date stringToDate(String dateStr, String format, int hours, int minutes, int seconds) {
		return stringToDate(dateStr, format, hours, minutes, seconds, 0);
	}

	/**
	 * 时间戳转换日期对象
	 * 
	 * @author BILL CHEN
	 * @date 2016年5月26日 上午10:36:16 参数说明
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static Date timeStampToDate(Long timeStamp) {
		return getCalendar(timeStamp).getTime();
	}

	/**
	 * 操作时间戳转换日期，清空日期之后，保留年份和月份 @author： xw
	 * 
	 * @date: 2016年9月12日 上午11:02:34
	 *
	 * @param timeStamp
	 * @return Date
	 *
	 */
	public static Date timeStampToDate(Long timeStamp, String format) {
		String dateStr = timeStampToString(timeStamp, format);
		return stringToDate(dateStr, format);
	}

	/**
	 * 根据指定日期加减指定月份
	 * 
	 * @author BILL CHEN
	 * @date 2016年6月1日 下午12:33:54 参数说明
	 * 
	 * @param date
	 * @return
	 */
	public static Date addMonthByDate(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 根据指定日期加减指定天数
	 * 
	 * @author chenbiao
	 * @date 2016年9月19日 下午8:10:32 参数说明
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDaysByDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 时间字符串数据 增加减天数
	 *
	 * @author gonglb
	 * @date 2017年5月22日 上午9:25:35
	 */
	public static Date addDaysByDateStr(String dateStr, int days, String format) {
		Date date = stringToDate(dateStr, format);
		return addDaysByDate(date, days);
	}

	/**
	 * 时间字符串数据 增加减天数
	 *
	 * @author gonglb
	 * @date 2017年5月22日 上午9:25:35
	 */
	public static String addDayStrsByDateStr(String dateStr, int days, String format) {
		Date date = stringToDate(dateStr, format);
		Date addDaysByDate = addDaysByDate(date, days);
		return dateToString(addDaysByDate, format);
	}

	/**
	 * 时间字符串数据 增加减天数
	 *
	 * @author gonglb
	 * @date 2017年5月22日 上午9:25:35
	 */
	public static String addDayStrsByDate(Date date, int days, String format) {
		Date addDaysByDate = addDaysByDate(date, days);
		return dateToString(addDaysByDate, format);
	}

	/**
	 * 根据时间戳返回指定天数内的时间字符串集合
	 * 
	 * @author chenbiao
	 * @date 2016年9月18日 下午4:59:05 参数说明
	 * 
	 * @param timeStamp
	 * @param days
	 * @return
	 */
	public static List<String> getDateByTimeStamp(long timeStamp, int days) {
		if (days > 0 && timeStamp > 0) {
			Calendar calendar = getCalendar(timeStamp);
			List<String> dates = new LinkedList<String>();
			for (int i = 0; i < days; i++) {
				if (i > 0)
					calendar.add(Calendar.DATE, 1);
				dates.add(dateToString(calendar.getTime(), yyyy_MM_dd));
			}
			return dates;
		}
		return null;
	}

	public static int getHours(Date date) {
		return getCalendar(date).get(Calendar.HOUR_OF_DAY);
	}

	public static int getHours(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 清空小时之后的时间数据，只保留年月 @author： xw
	 * 
	 * @date: 2016年9月12日 上午11:04:40
	 *
	 * @param date
	 * @return Date
	 *
	 */
	public static Date clearDayAndLater(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 
	 * 清空小时之后的时间数据，只保留年月日 @author： xw
	 * 
	 * @date: 2016年9月12日 上午10:57:27
	 *
	 * @param date
	 * @param months
	 * @return Date
	 *
	 */
	public static Date clearHourAndLater(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的23点59分59秒999毫秒
	 * 
	 * @author chenbiao
	 * @date 2018年9月20日 下午3:33:55 参数说明
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMillisecondOfDayByDate(Date date) {
		Calendar cal = getCalendar(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取年份
	 * 
	 * @param instance
	 * @return
	 */
	public static int getYear(Calendar instance) {
		return instance.get(Calendar.YEAR);
	}

	public static int getYear(Date date) {
		return getCalendar(date).get(Calendar.YEAR);
	}

	public static int getYear(Long date) {
		return getCalendar(date).get(Calendar.YEAR);
	}
	
	public static int getDay(Date date) {
		return getCalendar(date).get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定时间区间(自然时间区间)
	 * 
	 * @author chenbiao
	 * @date 2017年10月10日 下午9:42:45 参数说明
	 * 
	 * @return
	 */
	public static Long[] getBetweenTimeStamp(int type) {
		if (type <= 0) {
			return null;
		}
		Long[] times = new Long[2];
		Calendar calendar = getCalendar();
		switch (type) {
		case 1:// 一周以内
			times[1] = calendar.getTimeInMillis();
			times[0] = addDaysByDate(calendar.getTime(), -7).getTime();
			return times;
		case 2:// 一个月以内
			times[1] = calendar.getTimeInMillis();
			times[0] = addMonthByDate(calendar.getTime(), -1).getTime();
			return times;
		case 3:// 一至三个月内
			times[1] = addMonthByDate(calendar.getTime(), -1).getTime();
			times[0] = addMonthByDate(calendar.getTime(), -3).getTime();
			return times;
		case 4:// 三至六个月内
			times[1] = addMonthByDate(calendar.getTime(), -3).getTime();
			times[0] = addMonthByDate(calendar.getTime(), -6).getTime();
			return times;
		case 5:// 六个月以上
			times[1] = addMonthByDate(calendar.getTime(), -6).getTime();
			times[0] = null;// 没有开始时间
			return times;
		default:
			break;
		}
		return null;
	}

	/**
	 * 时间戳转换成文本
	 * 
	 * @author chenbiao
	 * @date 2017年11月10日 下午5:13:04 参数说明
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(long timestamp) {
		long current = getTimeStamp() - timestamp;
		if (current > 0 && current < DAY_TO_LONG) {
			// 同一天
			return timeStampToString(timestamp, HH_mm);
		} else if (current > DAY_TO_LONG && current < (DAY_TO_LONG * 2)) {
			// 超出一天（昨天）
			return YESTER_DAY_TEXT + " " + timeStampToString(timestamp, HH_mm);
		} else {
			return timeStampToString(timestamp, yyyy_MM_dd);
		}
	}

	/**
	 * 浏览记录日期拆剪
	 * 
	 * @author chenbiao
	 * @date 2017年11月14日 下午5:41:41 参数说明
	 * 
	 * @param date
	 * @return
	 */
	public static String customDateBySeeLog(String date) {
		if (null != date) {
			String year = CalendarUtils.dateToString(getDate(), yyyy);
			if (date.indexOf(year) >= 0) {
				return date.substring(year.length() + 1);
			}
		}
		return date;
	}
	
	/**
	 * 自定义年月设置时间
	* @author chenbiao
	* @date 2019年3月10日 下午2:40:34
	* 参数说明 
	* 
	* @param year
	* @param month
	* @return
	 */
	public static Date setDate(int year, int month) {
		Calendar cal = getCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 判断当前时间是否在时间段内
	 * 
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime,Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
 
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);
 
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
 
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据日期获取星期
	 * @param date
	 * @return
	 */
	public static  String getWeek(Date date){
		if(date!=null){
			Calendar calendar=getCalendar(date);
			int i=calendar.get(Calendar.DAY_OF_WEEK)-1;
			return weeks[i];
		}
		return null;
	}

	//java获取当前月的天数
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}

	public static void main(String[] args) throws ParseException {

	//	System.out.println(getDay(getDate()));
		System.out.println(getYear(CalendarUtils.stringToDate("2019-7-28", CalendarUtils.yyyy_MM_dd)));
//		System.out.println(getMonthSpace(stringToDate("2018-10-11", CalendarUtils.yyyy_MM_dd), stringToDate("2019-02-01", CalendarUtils.yyyy_MM_dd)));
//		System.out.println(CalendarUtils.dateToString(CalendarUtils.setMonth(Calendar.getInstance(), 2).getTime(),
//				CalendarUtils.yyyy_MM_dd));
		// System.out.println(customDateBySeeLog("2016-12-12"));
		// System.out.println(customDateBySeeLog("2017年12月12日"));
		// Long[] aa = getBetweenTimeStamp(1);
		// System.out.println(timeStampToString(aa[0], yyyy_MM_dd__HH_mm_ss));
		// System.out.println(timeStampToString(aa[1], yyyy_MM_dd__HH_mm_ss));
		//
		// System.out.println("-----------------");
		// Long[] bb = getBetweenTimeStamp(2);
		// System.out.println(timeStampToString(bb[0], yyyy_MM_dd__HH_mm_ss));
		// System.out.println(timeStampToString(bb[1], yyyy_MM_dd__HH_mm_ss));
		//
		// System.out.println("-----------------");
		// Long[] cc = getBetweenTimeStamp(3);
		// System.out.println(timeStampToString(cc[0], yyyy_MM_dd__HH_mm_ss));
		// System.out.println(timeStampToString(cc[1], yyyy_MM_dd__HH_mm_ss));
		// System.out.println("-----------------");
		//
		// Long[] dd = getBetweenTimeStamp(4);
		// System.out.println(timeStampToString(dd[0], yyyy_MM_dd__HH_mm_ss));
		// System.out.println(timeStampToString(dd[1], yyyy_MM_dd__HH_mm_ss));
		// System.out.println("-----------------");
		// Long[] ee = getBetweenTimeStamp(5);
		// System.out.println(timeStampToString(ee[1], yyyy_MM_dd__HH_mm_ss));
	}

}
