package com.appz9001.boot.dto.status;

import com.appz9001.boot.dto.RoomInfoDto;

import java.util.List;
import java.util.Map;

public class RoomStatusPageDto {
    private RoomStatusSumDto roomStatusSumDto;
    private Map<String, List<RoomInfoDto>> roomMap;

    public RoomStatusSumDto getRoomStatusSumDto() {
        return roomStatusSumDto;
    }

    public void setRoomStatusSumDto(RoomStatusSumDto roomStatusSumDto) {
        this.roomStatusSumDto = roomStatusSumDto;
    }

    public Map<String, List<RoomInfoDto>> getRoomMap() {
        return roomMap;
    }

    public void setRoomMap(Map<String, List<RoomInfoDto>> roomMap) {
        this.roomMap = roomMap;
    }
}
