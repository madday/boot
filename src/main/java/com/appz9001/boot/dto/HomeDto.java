package com.appz9001.boot.dto;

import java.util.List;

public class HomeDto {
    public IncomeDto getIncomeDto() {
        return incomeDto;
    }

    public void setIncomeDto(IncomeDto incomeDto) {
        this.incomeDto = incomeDto;
    }

    public RentRateDto getRentRateDto() {
        return rentRateDto;
    }

    public void setRentRateDto(RentRateDto rentRateDto) {
        this.rentRateDto = rentRateDto;
    }

    private IncomeDto incomeDto;
    private RentRateDto rentRateDto;
    private RoomSumInfo roomSumInfo;

    public RoomSumInfo getRoomSumInfo() {
        return roomSumInfo;
    }

    public void setRoomSumInfo(RoomSumInfo roomSumInfo) {
        this.roomSumInfo = roomSumInfo;
    }

    private List<RoomStatusDto> weekRateList;

    private List<String> weekList;

    public List<RoomStatusDto> getWeekRateList() {
        return weekRateList;
    }

    public void setWeekRateList(List<RoomStatusDto> weekRateList) {
        this.weekRateList = weekRateList;
    }

    public List<String> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<String> weekList) {
        this.weekList = weekList;
    }

    private CheckInfoDto checkInfoDto;

    public CheckInfoDto getCheckInfoDto() {
        return checkInfoDto;
    }

    public void setCheckInfoDto(CheckInfoDto checkInfoDto) {
        this.checkInfoDto = checkInfoDto;
    }
}
