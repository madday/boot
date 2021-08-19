package com.appz9001.boot.dto.status;

public class RoomStatusSumDto {
    private int emptyClean;
    private int emptyDirty;
    private int liveIn;
    private int liveDirty;
    private int liveClean;
    private int preCheck;
    private int preOut;
    private int repair;
    private int prepare;
    private int all;

    public int getEmptyClean() {
        return emptyClean;
    }

    public int getEmptyDirty() {
        return emptyDirty;
    }

    public int getLiveIn() {
        return liveIn;
    }

    public int getLiveDirty() {
        return liveDirty;
    }

    public int getLiveClean() {
        return liveClean;
    }

    public int getPreCheck() {
        return preCheck;
    }

    public int getPreOut() {
        return preOut;
    }

    public int getRepair() {
        return repair;
    }

    public int getPrepare() {
        return prepare;
    }

    public int getAll() {
        return all;
    }

    public void setEmptyClean(int emptyClean) {
        this.emptyClean = emptyClean;
    }

    public void setEmptyDirty(int emptyDirty) {
        this.emptyDirty = emptyDirty;
    }

    public void setLiveIn(int liveIn) {
        this.liveIn = liveIn;
    }

    public void setLiveDirty(int liveDirty) {
        this.liveDirty = liveDirty;
    }

    public void setLiveClean(int liveClean) {
        this.liveClean = liveClean;
    }

    public void setPreCheck(int preCheck) {
        this.preCheck = preCheck;
    }

    public void setPreOut(int preOut) {
        this.preOut = preOut;
    }

    public void setRepair(int repair) {
        this.repair = repair;
    }

    public void setPrepare(int prepare) {
        this.prepare = prepare;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
