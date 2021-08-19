package com.appz9001.boot.mapper;

import com.appz9001.boot.dto.*;
import com.appz9001.boot.dto.yesterday.*;

import java.util.List;
import java.util.Map;

public interface AppRoomYesterdayMapper {

    public List<BillInfoDto> queryBill(Map<String,String> params);

    public SettleBillDto querySettleBill(Map<String,String> params);

    public List<RoomSortInfo> querySaleInfo(Map<String,String> params);

    public Long queryMemCnt(Map<String,String> params);

    public MemberInfoDto queryMemInfo(Map<String,String> params);

    public SettleBillDto queryReturnMoney(Map<String,String> params);

    public CouponDto queryCoupon(Map<String,String> params);

    public MemberInfoDto queryMemConsum(Map<String,String> params);
}
