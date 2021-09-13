package com.appz9001.boot.dto.account;

public class RoomAccountDto {
    private String accountId;
    private String accoName;
    private String accoSort;
    private String checkinTime;
    private String checkoutTime;
    private String roomPriceDisp;
    private String prepay;
    private String saleMoney;
    private String banlance;
    private String groupShort;

    public String getAccoName() {
        return accoName;
    }

    public void setAccoName(String accoName) {
        this.accoName = accoName;
    }

    public String getAccoSort() {
        return accoSort;
    }

    public void setAccoSort(String accoSort) {
        this.accoSort = accoSort;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getRoomPriceDisp() {
        return roomPriceDisp;
    }

    public void setRoomPriceDisp(String roomPriceDisp) {
        this.roomPriceDisp = roomPriceDisp;
    }

    public String getPrepay() {
        return prepay;
    }

    public void setPrepay(String prepay) {
        this.prepay = prepay;
    }

    public String getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(String saleMoney) {
        this.saleMoney = saleMoney;
    }

    public String getBanlance() {
        return banlance;
    }

    public void setBanlance(String banlance) {
        this.banlance = banlance;
    }

    public String getGroupShort() {
        return groupShort;
    }

    public void setGroupShort(String groupShort) {
        this.groupShort = groupShort;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
