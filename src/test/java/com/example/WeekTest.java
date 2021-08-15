package com.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WeekTest {
    public static void main(String[] args){
        LocalDate listDays = LocalDate.now();
        System.out.println(listDays.toString());
        System.out.println(Arrays.asList(DayOfWeek.values()).stream().map(listDays::with).collect(Collectors.toList()));
    }
}
