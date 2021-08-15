package com.appz9001.boot.dto;

import java.io.Serializable;

public class IncomeDto{
    private double dayMoney;
    private double monthMoney;
    private double yearMoney;

    private String dayIncome;

    public String getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(String dayIncome) {
        this.dayIncome = dayIncome;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(String yearIncome) {
        this.yearIncome = yearIncome;
    }

    private String monthIncome;
    private String yearIncome;

    public double getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(double dayMoney) {
        this.dayMoney = dayMoney;
    }

    public double getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(double monthMoney) {
        this.monthMoney = monthMoney;
    }

    public double getYearMoney() {
        return yearMoney;
    }

    public void setYearMoney(double yearMoney) {
        this.yearMoney = yearMoney;
    }

}
