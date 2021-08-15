package com.appz9001.boot.dto;

public class RentRateDto {
    private Long rentNum;

    public Long getRentNum() {
        return rentNum;
    }

    public void setRentNum(Long rentNum) {
        this.rentNum = rentNum;
    }

    public long getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Long roomNum) {
        this.roomNum = roomNum;
    }

    private Long roomNum;

    private String rentRate;

    public String getRentRate() {
        return rentRate;
    }

    public void setRentRate(String rentRate) {
        this.rentRate = rentRate;
    }
}
