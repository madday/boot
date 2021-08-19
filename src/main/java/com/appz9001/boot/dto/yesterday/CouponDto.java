package com.appz9001.boot.dto.yesterday;

public class CouponDto {
    private Double payMoneyA = 0d; // 代金券
    private Double zhaodaiMoney = 0d; // 招待
    private Double payMoneyC = 0d; // 挂账

    public Double getPayMoneyA() {
        return payMoneyA;
    }

    public void setPayMoneyA(Double payMoneyA) {
        this.payMoneyA = payMoneyA;
    }

    public Double getPayMoneyC() {
        return payMoneyC;
    }

    public void setPayMoneyC(Double payMoneyC) {
        this.payMoneyC = payMoneyC;
    }

    public Double getZhaodaiMoney() {
        return zhaodaiMoney;
    }

    public void setZhaodaiMoney(Double zhaodaiMoney) {
        this.zhaodaiMoney = zhaodaiMoney;
    }
}
