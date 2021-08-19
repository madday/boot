package com.appz9001.boot.dto;

public class RoomStatusDto {
    private String rentRate = "0";
    private Double sAll = 0d;

    private Integer cNightcheck;
    private Integer cMooning;
    private Integer cNooning;
    private Integer cEvining;
    private Integer cDay;
    private Integer cClock;

    private Double sNightcheck = 0d;
    private Double sMooning;
    private Double sNooning;
    private Double sEvining;
    private Double sDay;
    private Double sClock;

    public Integer getcNightcheck() {
        return cNightcheck;
    }

    public void setcNightcheck(Integer cNightcheck) {
        this.cNightcheck = cNightcheck;
    }

    public Integer getcMooning() {
        return cMooning;
    }

    public void setcMooning(Integer cMooning) {
        this.cMooning = cMooning;
    }

    public Integer getcNooning() {
        return cNooning;
    }

    public void setcNooning(Integer cNooning) {
        this.cNooning = cNooning;
    }

    public Integer getcEvining() {
        return cEvining;
    }

    public void setcEvining(Integer cEvining) {
        this.cEvining = cEvining;
    }

    public Integer getcDay() {
        return cDay;
    }

    public void setcDay(Integer cDay) {
        this.cDay = cDay;
    }

    public Integer getcClock() {
        return cClock;
    }

    public void setcClock(Integer cClock) {
        this.cClock = cClock;
    }

    public Double getsNightcheck() {
        return sNightcheck;
    }

    public void setsNightcheck(Double sNightcheck) {
        this.sNightcheck = sNightcheck;
    }

    public Double getsMooning() {
        return sMooning;
    }

    public void setsMooning(Double sMooning) {
        this.sMooning = sMooning;
    }

    public Double getsNooning() {
        return sNooning;
    }

    public void setsNooning(Double sNooning) {
        this.sNooning = sNooning;
    }

    public Double getsEvining() {
        return sEvining;
    }

    public void setsEvining(Double sEvining) {
        this.sEvining = sEvining;
    }

    public Double getsDay() {
        return sDay;
    }

    public void setsDay(Double sDay) {
        this.sDay = sDay;
    }

    public Double getsClock() {
        return sClock;
    }

    public void setsClock(Double sClock) {
        this.sClock = sClock;
    }

    public Double getsAll() {
        return sAll;
    }

    public void setsAll(Double sAll) {
        this.sAll = sAll;
    }

    public String getRentRate() {
        return rentRate;
    }

    public void setRentRate(String rentRate) {
        this.rentRate = rentRate;
    }
}
