package com.vendixxx.monitor.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author liuzheng
 * @date 2019-11-12
 * @since 2019
 */
public class DateInvUtils {

    public static final String HOUR_MINUTE_SECOND_BEGIN = " 00:00:00";

    public static final String HOUR_MINUTE_SECOND_END = " 23:59:59";


    public static final String YYYYMMDD_STR = "yyyyMMdd";

    /**
     * 日期格式，yyyyMMdd: "yyyy-MM-dd"
     */
    public static final String YYYY_MM_DD_STR = "yyyy-MM-dd";

    /**
     * 日期格式，yyyyMMddhhmmss: "yyyy-MM-dd kk:mm:ss"
     */
    public static final String YYYY_MM_DD_HH_MM_SS_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式
     */
    public static final String YYYY_MM_DD_HH_MM_SS_CHINESE_SMIPLE_DESC = "yyyy年MM月dd日HH点mm分ss秒";

    public DateInvUtils() {

    }

    /**
     * 时间加减
     */
    public static Date getDateAfterOpTime(Date date,int field,int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field,amount);
        return cal.getTime();
    }

    public static String dateToStr(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date strToDate(String dateStr,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String transferLongToDate(Long millSec,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(millSec.longValue());
        return sdf.format(date);
    }

    public static String getWeekDay() {
        return null;
    }

    public static Date getDate(String dateString, String format) {
        if (StringUtils.isNotEmpty(dateString)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                return simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getDate(Date date, String format) {
        if (null != date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        }
        return "";
    }

    /**
     * 获取指定时间的年份
     * @param date
     * @return
     */
    public static int getYear(Date date){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sd.format(date);

        return Integer.parseInt(dateStr.substring(0, 4));

    }

    /**
     * 获取制定时间的月份
     * @param date
     * @return
     */
    public static int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;

    }

    /**
     * 获取制定时间的日
     * @param date
     * @return
     */
    public static int getDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getWeek(Date date){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

    //2015年7月5日 星期三
    public static String getStrDateAndWeed(Date date){
        String str = getYear(date) + "年" + getMonth(date) + "月" + getDay(date) + "日" + " " + getWeek(date);
        return str;
    }

    /**
     * 计算两个日期相差的天数
     * @param data1
     * @param data2
     * @return
     */
    public static Integer days(String data1, String data2){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Long c = null;
        try {
            c = sf.parse(data2).getTime()-sf.parse(data1).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long d = c/1000/60/60/24;//天
        int days = (int)d;
        return days;
    }

    /**
     * 日期增长
     * @param time
     * @param dayCount 增长天数
     * @return
     */
    public static String time(String time, int dayCount){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str="";
        try {
            Date date = sdf.parse(time);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, dayCount);//
            //把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.getTime();
            // 这个时间就是日期往后推一天的结果
            str= sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
