package com.appz9001.boot.dto.account;

import java.math.BigDecimal;

public class RoomAccountDto {
    private String accountId;
    private String accoName;
    private String accoSort;
    private String checkinTime;
    private String checkoutTime;
    private String roomPriceDisp;
    private BigDecimal prepay;
    private BigDecimal saleMoney;
    private BigDecimal banlance;
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

    public BigDecimal getPrepay() {
        return prepay;
    }

    public void setPrepay(BigDecimal prepay) {
        this.prepay = prepay;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public BigDecimal getBanlance() {
        return banlance;
    }

    public void setBanlance(BigDecimal banlance) {
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
