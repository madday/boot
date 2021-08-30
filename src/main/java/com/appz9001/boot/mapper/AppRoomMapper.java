package com.appz9001.boot.mapper;

import com.appz9001.boot.dto.*;

import java.util.List;
import java.util.Map;

public interface AppRoomMapper {

    public List<RoomInfoDto> queryRoomInfo();

    public List<IncomeDto> queryIncome(Map<String,String> param);

    public RentRateDto queryCurRentRate();

    public List<RoomStatusDto> queryRoomStatus(Map<String,String> param);

    public CheckInfoDto queryCheckInfo();

    public String querySysDate();

    public String querySysDateBefore();

    public Double querySaleMoney();
}
