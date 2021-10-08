package com.appz9001.boot.dto;

import java.math.BigDecimal;

public class CheckInfoDto {
    private BigDecimal checkIn;
    private BigDecimal checkOut;
    private BigDecimal checkInAll;
    private BigDecimal checkInPre;

    public BigDecimal getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(BigDecimal checkIn) {
        this.checkIn = checkIn;
    }

    public BigDecimal getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(BigDecimal checkOut) {
        this.checkOut = checkOut;
    }

    public BigDecimal getCheckInAll() {
        return checkInAll;
    }

    public void setCheckInAll(BigDecimal checkInAll) {
        this.checkInAll = checkInAll;
    }

    public BigDecimal getCheckInPre() {
        return checkInPre;
    }

    public void setCheckInPre(BigDecimal checkInPre) {
        this.checkInPre = checkInPre;
    }
}
