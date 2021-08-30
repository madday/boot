package com.appz9001.boot.dto;

public class RoomInfoDto {
    private String isTodayLeave;

    private String housingSort;

    private String checkinTime;

    private String bookDays;

    private String clearStatus;

    private String useStatus;

    private String roomId;
    private String guestName;
    private Double roomPrice;
    private String roomSort;
    private String dispRemark;
    private String roomPriceDisp;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomSort() {
        return roomSort;
    }

    public void setRoomSort(String roomSort) {
        this.roomSort = roomSort;
    }

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

    public String getDispRemark() {
        return dispRemark;
    }

    public void setDispRemark(String dispRemark) {
        this.dispRemark = dispRemark;
    }

    public String getRoomPriceDisp() {
        return roomPriceDisp;
    }

    public void setRoomPriceDisp(String roomPriceDisp) {
        this.roomPriceDisp = roomPriceDisp;
    }
}
