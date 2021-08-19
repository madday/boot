package com.appz9001.boot.dto.request;

import com.appz9001.boot.base.dto.BaseRequest;

public class RoomStatusRequest extends BaseRequest {
    private String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
