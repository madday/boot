package com.appz9001.boot.dto.yesterday;

public class RoomSortInfo {
    private String guestSortId;
    private String guestSort;

    private String sort;

    public String getGuestSortId() {
        return guestSortId;
    }

    public void setGuestSortId(String guestSortId) {
        this.guestSortId = guestSortId;
    }

    public String getGuestSort() {
        return guestSort;
    }

    public void setGuestSort(String guestSort) {
        this.guestSort = guestSort;
    }

    private Double cStart = 0d;
    private Double cEnd = 0d;
    private Double ci = 0d;
    private Double co = 0d;

    public Double getcStart() {
        return cStart;
    }

    public void setcStart(Double cStart) {
        this.cStart = cStart;
    }

    public Double getcEnd() {
        return cEnd;
    }

    public void setcEnd(Double cEnd) {
        this.cEnd = cEnd;
    }

    public Double getCi() {
        return ci;
    }

    public void setCi(Double ci) {
        this.ci = ci;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
