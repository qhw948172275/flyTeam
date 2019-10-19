package com.cdyykj.system.commons;

import java.math.BigDecimal;

public class BytesUtils {
    public static String bytes2kb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal gibibyte = new BigDecimal(1024*1024*1024);
        float returnValue = filesize.divide(gibibyte, 2, BigDecimal.ROUND_UP).floatValue();
        /*divide(BigDecimal divisor, int scale, int roundingMode) 返回一个BigDecimal，其值为（this/divisor），其标度为指定标度*/
        if(returnValue > 10) {
            return (returnValue + "GB");
        } else {
            BigDecimal mebibyte = new BigDecimal(1024*1024);
            returnValue = filesize.divide(mebibyte, 2, BigDecimal.ROUND_UP).floatValue();
            if(returnValue > 1) {
                return (returnValue + "MB");
            } else {
                BigDecimal kibibyte = new BigDecimal(1024);
                returnValue = filesize.divide(kibibyte, 2,BigDecimal.ROUND_UP).floatValue();
                return (returnValue + "KB");
            }
        }
    }
}
