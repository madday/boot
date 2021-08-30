package com.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WeekTest {
    public static void main(String[] args){
        getWeekList();
    }

    public static void getWeekList(){
        String[] weeks = new String[]{"一","二","三","四","五","六","七"};
        LocalDate localDate = LocalDate.now();
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
    }
}
