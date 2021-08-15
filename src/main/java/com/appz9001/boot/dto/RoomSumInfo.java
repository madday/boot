package com.appz9001.boot.dto;

public class RoomSumInfo {
    //总房间
    private Integer roomNum = 0;
    //在住
    private Integer roomIn = 0;
    //净房
    private Integer roomClean = 0;
    //空脏
    private Integer roomDirty = 0;
    //预定
    private Integer roomBook = 0;
    //将离
    private Integer roomLeave = 0;
    //钟点
    private Integer roomTime = 0;
    //维修
    private Integer roomRepair = 0;
    //备用
    private Integer roomPrepare = 0;
    //全天
    private Integer roomWholeDay = 0;
    //自用
    private Integer roomSelf = 0;

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getRoomIn() {
        return roomIn;
    }

    public void setRoomIn(Integer roomIn) {
        this.roomIn = roomIn;
    }

    public Integer getRoomClean() {
        return roomClean;
    }

    public void setRoomClean(Integer roomClean) {
        this.roomClean = roomClean;
    }

    public Integer getRoomBook() {
        return roomBook;
    }

    public void setRoomBook(Integer roomBook) {
        this.roomBook = roomBook;
    }

    public Integer getRoomLeave() {
        return roomLeave;
    }

    public void setRoomLeave(Integer roomLeave) {
        this.roomLeave = roomLeave;
    }

    public Integer getRoomTime() {
        return roomTime;
    }

    public void setRoomTime(Integer roomTime) {
        this.roomTime = roomTime;
    }

    public Integer getRoomDirty() {
        return roomDirty;
    }

    public void setRoomDirty(Integer roomDirty) {
        this.roomDirty = roomDirty;
    }

    public Integer getRoomRepair() {
        return roomRepair;
    }

    public void setRoomRepair(Integer roomRepair) {
        this.roomRepair = roomRepair;
    }

    public Integer getRoomPrepare() {
        return roomPrepare;
    }

    public void setRoomPrepare(Integer roomPrepare) {
        this.roomPrepare = roomPrepare;
    }

    public Integer getRoomWholeDay() {
        return roomWholeDay;
    }

    public void setRoomWholeDay(Integer roomWholeDay) {
        this.roomWholeDay = roomWholeDay;
    }

    public Integer getRoomSelf() {
        return roomSelf;
    }

    public void setRoomSelf(Integer roomSelf) {
        this.roomSelf = roomSelf;
    }
}
