package com.cdyykj.system.commons;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtils {

    public static String encode(String password){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        password=passwordEncoder.encode(password);
        return password;
    }

    public static void main(String[] args) {
        System.out.println(CalendarUtils.getTimeStamp());
       System.out.println(CommonUtils.encode("123456"));
    }
}
