package com.appz9001.boot.dto;

import java.util.List;

public class WeekRateDto {
    private List<String> weekList;
    private List<String> dataList;

    public List<String> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<String> weekList) {
        this.weekList = weekList;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
