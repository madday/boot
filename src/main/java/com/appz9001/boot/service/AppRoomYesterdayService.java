package com.appz9001.boot.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.dto.*;
import com.appz9001.boot.dto.yesterday.BillInfoDto;
import com.appz9001.boot.dto.yesterday.RoomSortInfo;
import com.appz9001.boot.dto.yesterday.RoomYesterdayDto;
import com.appz9001.boot.dto.yesterday.SettleBillDto;
import com.appz9001.boot.mapper.AppRoomMapper;
import com.appz9001.boot.mapper.AppRoomYesterdayMapper;
import com.appz9001.boot.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppRoomYesterdayService {
    private static final Logger logger = LoggerFactory.getLogger(AppRoomYesterdayService.class);
    @Autowired
    private DataSoureService dataSourceService;
    @Autowired
    private AppRoomMapper appRoomMapper;
    @Autowired
    private AppRoomYesterdayMapper appRoomYesterdayMapper;

    public RoomYesterdayDto queryRoomYesterday(String date,String userId){
        RoomYesterdayDto yesterdayDto = new RoomYesterdayDto();
        String yesterday = DateUtil.getYesterday();
        userId = "1111";
        try{
            DataSource dataSource = dataSourceService.getDataSource(userId);
            DynamicDataSource.dataSourcesMap.put(userId, dataSource);
            DynamicDataSource.setDataSource(userId);
            Map<String,String> params = new HashMap<>();
            params.put("start",yesterday);
            params.put("end",yesterday);
            // 账单信息
            List<BillInfoDto> billInfoDto = appRoomYesterdayMapper.queryBill(params);
            yesterdayDto.setBillInfoDto(billInfoDto);
            logger.info("账单信息：{}",JSON.toJSONString(billInfoDto));

            //结算情况
            SettleBillDto settleBillDto = appRoomYesterdayMapper.querySettleBill(params);
            yesterdayDto.setSettleBillDto(settleBillDto);
            logger.info("结算情况：{}",JSON.toJSONString(billInfoDto));

            //产品销量
            List<RoomSortInfo> sortList = this.appRoomYesterdayMapper.querySaleInfo(params);
            logger.info("产品销量：{}",JSON.toJSONString(sortList));
            yesterdayDto.setRoomSortList(sortList);
            List<RoomSortInfo> bedSortList = new ArrayList<>();
            List<RoomSortInfo> cusSortList = new ArrayList<>();
            RoomSortInfo totalSortInfo = new RoomSortInfo();
            for(RoomSortInfo sortInfo:sortList){
                if("房型统计".equals(sortInfo.getSort())){
                    bedSortList.add(sortInfo);
                }
                else{
                    cusSortList.add(sortInfo);
                }
            }
            yesterdayDto.setBedSortList(bedSortList);
            yesterdayDto.setCusSortList(cusSortList);
            for(RoomSortInfo info:bedSortList){
                totalSortInfo.setcStart(totalSortInfo.getcStart()+info.getcStart());
                totalSortInfo.setcEnd(totalSortInfo.getcEnd()+info.getcEnd());
                totalSortInfo.setCi(totalSortInfo.getCi()+info.getCi());
                totalSortInfo.setCo(totalSortInfo.getCo()+info.getCo());
            }
            yesterdayDto.setTotalSortInfo(totalSortInfo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            DynamicDataSource.clear();
        }
        return yesterdayDto;
    }
}
