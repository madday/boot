package com.appz9001.boot.dto;

public class RoomInfoDto {
    private String isTodayLeave;

    private String housingSort;

    private String checkinTime;

    private String bookDays;

    private String clearStatus;

    private String useStatus;

    public String getIsTodayLeave() {
        return isTodayLeave;
    }

    public void setIsTodayLeave(String isTodayLeave) {
        this.isTodayLeave = isTodayLeave;
    }

    public String getHousingSort() {
        return housingSort;
    }

    public void setHousingSort(String housingSort) {
        this.housingSort = housingSort;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getBookDays() {
        return bookDays;
    }

    public void setBookDays(String bookDays) {
        this.bookDays = bookDays;
    }

    public String getClearStatus() {
        return clearStatus;
    }

    public void setClearStatus(String clearStatus) {
        this.clearStatus = clearStatus;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }
}
