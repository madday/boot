package com.appz9001.boot.dto.yesterday;

public class SettleBillDto {
    private String billCount;
    private String zeroMoney;
    private String invoice;

    public String getBillCount() {
        return billCount;
    }

    public void setBillCount(String billCount) {
        this.billCount = billCount;
    }

    public String getZeroMoney() {
        return zeroMoney;
    }

    public void setZeroMoney(String zeroMoney) {
        this.zeroMoney = zeroMoney;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
