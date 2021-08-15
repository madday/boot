package com.appz9001.boot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyyMMdd");

    public static String getToday(){
        return SDF_YMD.format(new Date());
    }

    public static String getYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        return SDF_YMD.format(cal.getTime());
    }
}
