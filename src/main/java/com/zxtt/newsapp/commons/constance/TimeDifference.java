package com.zxtt.newsapp.commons.constance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDifference {


    /**
     * 得到一个时间到当前时间的秒数
     * @param startDate
     * @return
     */
    private static int calLastedTime(Date startDate){
        long a=new Date().getTime();
        long b = startDate.getTime();
        int c = (int) ((a - b) / 1000);
        return c;
    }

    private static final String MES="前";
    /**
     * 显示的时间差
     * @return
     */
    public static String messageTime(Date startDate){
        StringBuilder result = new StringBuilder();
        //秒
        int i = calLastedTime(startDate);;


        //分
        int minute=i/60;
        //时
        int hour=minute/60;
        //天
        int day=hour/24;


        //月
        int mm=day/30;


        //年
        int yy=mm/12;
        //多少秒前
        if(minute==0){
            return i+"秒"+MES;
         //多少分钟前
        }else if(hour==0){
            return  minute+"分钟"+MES;
         //多少小时前
        }else if(day==0){
            return hour +"小时"+ MES;
         //多少天前
        }else if(mm==0){
            return day+"天" + MES;
         //几个月前
        }else if(yy==0){
            return mm +"个月"+ MES;
        }else{
            return yy+"年"+MES;
        }
    }




}
