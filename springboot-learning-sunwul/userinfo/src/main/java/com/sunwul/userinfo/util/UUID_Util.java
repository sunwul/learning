package com.sunwul.userinfo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/*****
 * @author: Sunwul
 * @date: 2020/1/9 16:32
 * PS: 
 */
public class UUID_Util {
    /**
     * java自带的UUID util
     * 得到32位的uuid
     * */
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
    /**
     * 获取当前时间 毫秒级
     * */
    public static String getSystemTime(){
        long mill=System.currentTimeMillis();
        String systime=String.valueOf(mill);
        return systime;
    }
//    public static void main(String[] args) {
//        // 格式化时间
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        // a为am/pm的标记
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
//        // 获取当前时间
//        Date date = new Date();
//        // 输出已经格式化的现在时间（24小时制）
//        System.out.println("现在时间：" + sdf.format(date));
//        System.out.println(getSystemTime());
//        //截取UUID前8位
//        System.out.println(getUUID32().substring(0,8));
//    }
}
