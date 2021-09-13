package com.appz9001.boot.dto.account;

import java.util.List;
import java.util.Map;

public class RoomAccountRespDto {
    private Map<String,List<RoomAccountDto>> roomAccountDtoList;

    public Map<String, List<RoomAccountDto>> getRoomAccountDtoList() {
        return roomAccountDtoList;
    }

    public void setRoomAccountDtoList(Map<String, List<RoomAccountDto>> roomAccountDtoList) {
        this.roomAccountDtoList = roomAccountDtoList;
    }
}
