package com.appz9001.boot.dto.yesterday;

import com.appz9001.boot.dto.RoomStatusDto;

import java.util.List;

public class RoomYesterdayDto {
    private List<BillInfoDto> billInfoList;
    private SettleBillDto settleBillDto;
    private List<RoomSortInfo> roomSortList;
    private List<RoomSortInfo> bedSortList;
    private List<RoomSortInfo> cusSortList;
    private RoomStatusDto roomStatusDto;
    private MemberInfoDto memberInfoDto;
    private CouponDto couponDto;
    private RoomSortInfo totalSortInfo;
    private String sDate;
    private String yesDate;


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

    public RoomStatusDto getRoomStatusDto() {
        return roomStatusDto;
    }

    public void setRoomStatusDto(RoomStatusDto roomStatusDto) {
        this.roomStatusDto = roomStatusDto;
    }

    public MemberInfoDto getMemberInfoDto() {
        return memberInfoDto;
    }

    public void setMemberInfoDto(MemberInfoDto memberInfoDto) {
        this.memberInfoDto = memberInfoDto;
    }

    public CouponDto getCouponDto() {
        return couponDto;
    }

    public void setCouponDto(CouponDto couponDto) {
        this.couponDto = couponDto;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getYesDate() {
        return yesDate;
    }

    public void setYesDate(String yesDate) {
        this.yesDate = yesDate;
    }
}
