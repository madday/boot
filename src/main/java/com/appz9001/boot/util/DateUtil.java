package com.appz9001.boot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    private static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyyMMdd");

    private static final SimpleDateFormat SDF_YMD_A = new SimpleDateFormat("yyyy-MM-dd");


    public static String getToday(){
        return SDF_YMD.format(new Date());
    }

    public static String getYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        return SDF_YMD.format(cal.getTime());
    }

    public static List<String> getWeekList(){
        String[] weeks = new String[]{"一","二","三","四","五","六","七"};
        LocalDate localDate = LocalDate.now().minusDays(1);
        int value = localDate.getDayOfWeek().getValue();
        System.out.println(value);

        List<String> weeksList = new ArrayList<>();
        for(int i=value ; i<7;i++){
            weeksList.add(weeks[i]);
        }
        for(int i=0;i<value;i++){
            weeksList.add(weeks[i]);
        }
        System.out.println(weeksList);
        return weeksList;
    }

    public static String dateBefore(String dateStr, int i){
        try {
            Date date = SDF_YMD_A.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE,i);
            return SDF_YMD_A.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
