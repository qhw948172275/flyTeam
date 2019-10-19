package com.cdyykj.system.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtils {

	public static final long DEFAULT_STR_LENGTH = 10L;
	/**
	 * 特殊字符
	 */
	private static final char[] SPECIAL_CHARS = { '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/', '?' };

	/**
	 * 获取指定个数以及指定长度的随机数(字母+数字)
	 * 
	 * @param count
	 * @param length
	 * @return
	 */
	public static Set<String> getRandomSet(int count, long length, Set<String> set) {
		if (count <= 0 || length <= 0) {
			return null;
		}
		String random = null;
		for (int i = 0; i < count; i++) {
			random = getRandomCharAndNumr(length);
			if (isRandomUsable(random) || set.contains(random)) {
				i--;
				continue;
			}
			set.add(random);
		}
		return set;
	}

	public static String getRandomCode(long length) {
		String randomCode = null;
		for (int i = 0; i > -1; i++) {
			randomCode = getRandomCharAndNumr(length);
			if (!isRandomUsable(randomCode)) {
				return randomCode;
			}
		}
		return null;
	}

	/**
	 * 获取随机字母数字组合
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomCharAndNumr(long length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串
				int choice = random.nextBoolean() ? 65 : 97; // 取得65大写字母还是97小写字母
				str += (char) (choice + random.nextInt(26));// 取得大写字母
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}
	/**
	 * 生成字母加数字返回大写状态的随机字符串
	* @author chenbiao
	* @date 2017年8月4日 下午3:41:04
	* 参数说明 
	* 
	* @param length
	* @return
	 */
	public static String getRandomCharAndNumrToUpperCase(long length) {
		return getRandomCharAndNumr(length).toUpperCase();
	}

	/**
	 * 随机数字
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumr(long length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str += String.valueOf(random.nextInt(10));
		}
		if(str.length() < length){
			
		}
		return str;
	}
	/**
	 * 获取指定长度的随机数字
	* @author chenbiao
	* @date 2018年5月30日 下午7:07:43
	* 参数说明 
	* 
	* @param str
	* @param length
	* @return
	 */
	public static String getRandomNumr(String str,long length) {
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str += String.valueOf(random.nextInt(10));
		}
		if(str.length() < length){
			
		}
		return str;
	}
	
	

	/**
	 * 验证随机字母数字组合是否纯数字与纯字母
	 * 
	 * @param str
	 * @return true 是 ， false 否
	 */
	public static boolean isRandomUsable(String str) {
		// String regExp =
		// "^[A-Za-z]+(([0-9]+[A-Za-z0-9]+)|([A-Za-z0-9]+[0-9]+))|[0-9]+(([A-Za-z]+[A-Za-z0-9]+)|([A-Za-z0-9]+[A-Za-z]+))$";
		String regExp = "^([0-9]+)|([A-Za-z]+)$";
		Pattern pat = Pattern.compile(regExp);
		Matcher mat = pat.matcher(str);
		return mat.matches();
	}

	/**
	 * 生成订单编号
	 * 
	 * @author cym
	 * @date 2017年2月28日 上午10:27:34
	 */
	public static String getStuNumber() {
		return CalendarUtils.dateToString(CalendarUtils.getDate(), CalendarUtils.yyyyMMdd) + getRandomNumr(5);
	}
	/**
	 * 
	* 获取订单号
	* @author chenbiao
	* @date 2018年9月19日 下午3:04:10
	* 参数说明 
	* 
	* @return
	 */
	public static String getOrderNumber() {
		return CalendarUtils.dateToString(CalendarUtils.getDate(), CalendarUtils.yyyyMMddHHmmss) + getRandomNumr(5);
	}

	/**
	 * 生成角色编码
	 * @return
	 */
	public static String getRoleCode(){
		return "SR"+ CalendarUtils.dateToString(CalendarUtils.getDate(), CalendarUtils.yyyyMMddHHmmss) + getRandomNumr(5);
	}

	/**
	 * 生产自定义前缀代码
	 * @param custom
	 * @return
	 */
	public static String getCustomCode(String custom){
		return custom+ CalendarUtils.dateToString(CalendarUtils.getDate(), CalendarUtils.yyyyMMddHHmmss) + getRandomNumr(5);
	}

	/**
	 * 根据名称首字母生成代码
	 * @param param
	 * @return
	 */
	public static String getAlphaCode(String param){
		return  PinyinUtils.getAlpha(param)+ CalendarUtils.dateToString(CalendarUtils.getDate(), CalendarUtils.yyyyMMddHHmmss);
	}
	 /**
     * 生成僵尸粉生日
     */
    public static String randomDateBetweenMinAndMax(){  
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();  
        //注意月份要减去1  
        calendar.set(1950,0,0);  
        calendar.getTime().getTime();  
        //根据需求，这里要将时分秒设置为0  
        calendar.set(Calendar.HOUR_OF_DAY, 0);  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND,0);  
        long min = calendar.getTime().getTime();;  
        calendar.set(2017,0,0);  
        calendar.set(Calendar.HOUR_OF_DAY,0);  
        calendar.set(Calendar.MINUTE,0);  
        calendar.set(Calendar.SECOND,0);  
        calendar.getTime().getTime();  
        long max = calendar.getTime().getTime();  
        //得到大于等于min小于max的double值  
        double randomDate = Math.random()*(max-min)+min;  
        //将double值舍入为整数，转化成long类型  
        calendar.setTimeInMillis(Math.round(randomDate));  
        return sdf.format(calendar.getTime());  
    }
    
    /**
     * 默认生成32位随机数字+字符+特殊字符的随机数
     * @return
     */
    public static String randomPassByCharAndNumberAndSpecialChar(){
    	return randomPassByCharAndNumberAndSpecialChar(32);
    }
    /**
     * 生成指定长度的随机字符串（字母+数字+特殊字符）
     * @param length
     * @return
     */
    public static String randomPassByCharAndNumberAndSpecialChar(int length){
    	byte[] genChances = { 3, 5, 2 }; //分别是生成数字、字母、特殊符号的概率,数字概率为20%，字母概率为50%，特殊字符概率为30%
    	return new String(generateRandomPassword(length, genChances));
    }
    
    
    private static final byte INDEX_NUMBER = 0;
    private static final byte INDEX_LETTER = 1;
    private static final byte INDEX_SPECIAL_CHAR = 2;
    
    /**
     * 按一定的概率生成一个随机的N位(N>=3)密码，必须由字母数字特殊符号组成，三者缺一不可
     * 
     * @param len
     *            密码长度,必须大于等于3
     *            分别是生成数字、字母、特殊符号的概率
     * @return 生成的随机密码
     */
    private static char[] generateRandomPassword(final int len, final byte[] paramGenChances) throws IllegalArgumentException {
        if (len < 3) {
            throw new IllegalArgumentException("len must not smaller than 3, but now is " + len);
        }
        final char[] password = new char[len];
        // 之所以该复制一份是为了使函数不对外产生影响
        final byte[] genChances = paramGenChances.clone();
        final byte[] genNums = new byte[genChances.length];
        for (byte i = 0; i < genChances.length; i++) {
            genNums[i] = 0;
        }
        final Random random = new Random();
        int r;
        for (int i = 0; i < len; i++) {
            adjustGenChance(len, i, genChances, genNums);
            byte index = getPasswordCharType(random, genChances);
            genNums[index]++;
            switch (index) {
            case INDEX_NUMBER:
                password[i] = (char) ('0' + random.nextInt(10));
                break;
            case INDEX_LETTER:
                r = random.nextInt(52);
                if (r < 26) {
                    password[i] = (char) ('A' + r);
                } else {
                    password[i] = (char) ('a' + r - 26);
                }
                break;
            case INDEX_SPECIAL_CHAR:
                r = random.nextInt(SPECIAL_CHARS.length);
                password[i] = SPECIAL_CHARS[r];
                break;
            default:
                password[i] = ' ';
                break;
            }
        }
        logChances(genNums);
        return password;
    }

    /**
     * 根据当前需要生成密码字符的位置,动态调整生成概率
     * 
     * @param len
     *            待生成的总长度
     * @param index
     *            当前位置
     * @param genChances
     *            分别是生成数字、字母、特殊符号的概率
     * @param genNums
     *            这些类型已经生成过的次数
     */
    private static void adjustGenChance(final int len, final int index, final byte[] genChances, final byte[] genNums) {
        final int leftCount = len - index;
        byte notGenCount = 0;
        for (byte i = 0; i < genChances.length; i++) {
            if (genNums[i] == 0) {
                notGenCount++;
            }
        }
        if (notGenCount > 0 && leftCount < genChances.length && leftCount == notGenCount) {
            for (byte i = 0; i < genChances.length; i++) {
                if (genNums[i] > 0) {
                    genChances[i] = 0;
                }
            }
        }
    }

    /**
     * 获取该密码字符的类型
     * 
     * @param random
     *            随机数生成器
     * @param genChances
     *            分别是生成数字、字母、特殊符号的概率
     * @return 密码字符的类型
     */
    private static byte getPasswordCharType(final Random random, final byte[] genChances) {
        int total = 0;
        byte i = 0;
        for (; i < genChances.length; i++) {
            total += genChances[i];
        }
        int r = random.nextInt(total);
        for (i = 0; i < genChances.length; r -= genChances[i], i++) {
            if (r < genChances[i]) {
                break;
            }
        }
        return i;
    }
    
    /**
     * 打印生成密码中各类字符的个数
     */
    private static void logChances(byte[] genNums) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (byte i = 0; i < genNums.length; i++) {
            sb.append(genNums[i]);
            if (i != genNums.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        System.out.println(sb.toString());
    }
    /**
     * 
    * 格式化数字长度，转换指定长度的数字信息
    * @author chenbiao
    * @date 2019年2月22日 下午3:46:26
    * 参数说明 
    * 
    * @param code
    * @param num
    * @return
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0     
        // num 代表长度为4     
        // d 代表参数为正数型 
        result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);
 
        return result;
    }
    /**
     * 获取发票号
    * @author chenbiao
    * @date 2019年2月24日 上午11:03:51
    * 参数说明 
    * 
    * @param
    * @return
     */
    public static String getNumberByReceipt(int id) {
        return "A"+autoGenericCode(String.valueOf(id), 5);
    }
    
    public static void main(String[] args) {
		System.out.println(getAlphaCode("班主任"));
	}

}
