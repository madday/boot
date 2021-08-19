package com.appz9001.boot.dto.yesterday;

public class SettleBillDto {
    private String billCount;
    private Double zeroMoney = 0d;
    private Double invoice = 0d;
    private Double discMoney = 0d;

    private Double fangfeiRetMoney = 0d;
    private Double xiaofeiRetMoney = 0d;

    public Double getFangfeiRetMoney() {
        return fangfeiRetMoney;
    }

    public void setFangfeiRetMoney(Double fangfeiRetMoney) {
        this.fangfeiRetMoney = fangfeiRetMoney;
    }

    public Double getXiaofeiRetMoney() {
        return xiaofeiRetMoney;
    }

    public void setXiaofeiRetMoney(Double xiaofeiRetMoney) {
        this.xiaofeiRetMoney = xiaofeiRetMoney;
    }

    public String getBillCount() {
        return billCount;
    }

    public void setBillCount(String billCount) {
        this.billCount = billCount;
    }

    public Double getZeroMoney() {
        return zeroMoney;
    }

    public void setZeroMoney(Double zeroMoney) {
        this.zeroMoney = zeroMoney;
    }

    public Double getInvoice() {
        return invoice;
    }

    public void setInvoice(Double invoice) {
        this.invoice = invoice;
    }

    public Double getDiscMoney() {
        return discMoney;
    }

    public void setDiscMoney(Double discMoney) {
        this.discMoney = discMoney;
    }
}
