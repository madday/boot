package com.appz9001.boot.dto.yesterday;

import java.util.List;

public class RoomYesterdayDto {
    private List<BillInfoDto> billInfoList;

    private SettleBillDto settleBillDto;

    private List<RoomSortInfo> roomSortList;

    private List<RoomSortInfo> bedSortList;
    private List<RoomSortInfo> cusSortList;

    public void setBillInfoList(List<BillInfoDto> billInfoList) {
        this.billInfoList = billInfoList;
    }

    public List<RoomSortInfo> getBedSortList() {
        return bedSortList;
    }

    public void setBedSortList(List<RoomSortInfo> bedSortList) {
        this.bedSortList = bedSortList;
    }

    public List<RoomSortInfo> getCusSortList() {
        return cusSortList;
    }

    public void setCusSortList(List<RoomSortInfo> cusSortList) {
        this.cusSortList = cusSortList;
    }

    public RoomSortInfo getTotalSortInfo() {
        return totalSortInfo;
    }

    public void setTotalSortInfo(RoomSortInfo totalSortInfo) {
        this.totalSortInfo = totalSortInfo;
    }

    private RoomSortInfo totalSortInfo;

    public List<BillInfoDto> getBillInfoList() {
        return billInfoList;
    }

    public void setBillInfoDto(List<BillInfoDto> billInfoList) {
        this.billInfoList = billInfoList;
    }

    public SettleBillDto getSettleBillDto() {
        return settleBillDto;
    }

    public void setSettleBillDto(SettleBillDto settleBillDto) {
        this.settleBillDto = settleBillDto;
    }

    public List<RoomSortInfo> getRoomSortList() {
        return roomSortList;
    }

    public void setRoomSortList(List<RoomSortInfo> roomSortList) {
        this.roomSortList = roomSortList;
    }
}
