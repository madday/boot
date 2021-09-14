package com.appz9001.boot.dto.account;

import java.util.List;
import java.util.Map;

public class RoomAccountRespDto {
    private Map<String,List<RoomAccountDto>> roomAccountDtoList;

    private RoomAccountDto accountSum;

    public Map<String, List<RoomAccountDto>> getRoomAccountDtoList() {
        return roomAccountDtoList;
    }

    public void setRoomAccountDtoList(Map<String, List<RoomAccountDto>> roomAccountDtoList) {
        this.roomAccountDtoList = roomAccountDtoList;
    }

    public RoomAccountDto getAccountSum() {
        return accountSum;
    }

    public void setAccountSum(RoomAccountDto accountSum) {
        this.accountSum = accountSum;
    }
}
