package com.appz9001.boot.dto.yesterday;

public class MemberInfoDto {
    private Long memNum;

    private Double money;

    private Double consumeMoney;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(Double freeMoney) {
        this.freeMoney = freeMoney;
    }

    private Double freeMoney;

    public Long getMemNum() {
        return memNum;
    }

    public void setMemNum(Long memNum) {
        this.memNum = memNum;
    }

    public Double getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Double consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

}
