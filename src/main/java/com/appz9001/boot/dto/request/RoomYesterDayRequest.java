package com.appz9001.boot.dto.request;

import com.appz9001.boot.base.dto.BaseRequest;

public class RoomYesterDayRequest extends BaseRequest {
    private String sdate;

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
}
