package com.appz9001.boot.mapper;

import com.appz9001.boot.dto.*;
import com.appz9001.boot.dto.yesterday.BillInfoDto;
import com.appz9001.boot.dto.yesterday.RoomSortInfo;
import com.appz9001.boot.dto.yesterday.RoomYesterdayDto;
import com.appz9001.boot.dto.yesterday.SettleBillDto;

import java.util.List;
import java.util.Map;

public interface AppRoomYesterdayMapper {

    public List<BillInfoDto> queryBill(Map<String,String> params);

    public SettleBillDto querySettleBill(Map<String,String> params);

    public List<RoomSortInfo> querySaleInfo(Map<String,String> params);
}
