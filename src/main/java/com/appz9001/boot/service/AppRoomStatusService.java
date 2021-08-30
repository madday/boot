package com.appz9001.boot.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.dto.*;
import com.appz9001.boot.dto.request.RoomStatusRequest;
import com.appz9001.boot.dto.status.RoomStatusPageDto;
import com.appz9001.boot.dto.status.RoomStatusSumDto;
import com.appz9001.boot.mapper.AppRoomMapper;
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
public class AppRoomStatusService {
    private static final Logger logger = LoggerFactory.getLogger(AppRoomStatusService.class);
    @Autowired
    private DataSoureService dataSourceService;
    @Autowired
    private AppRoomMapper appRoomMapper;

    public RoomStatusPageDto queryRoomStatus(RoomStatusRequest request){
        RoomStatusPageDto roomStatusPageDto = new RoomStatusPageDto();
        Map<String,List<RoomInfoDto>> map = new HashMap<>();
        try{
            String userId = request.getUserid();
            DataSource dataSource = dataSourceService.getDataSource(request.getUserid());
            DynamicDataSource.dataSourcesMap.put(userId, dataSource);
            DynamicDataSource.setDataSource(userId);
            List<RoomInfoDto> roomList = appRoomMapper.queryRoomInfo();
            List<RoomInfoDto> retList = new ArrayList<>();
            // 处理房屋状态汇总
            RoomStatusSumDto roomStatusSumDto = new RoomStatusSumDto();
            if(roomList!=null&&roomList.size()>0){
                for(RoomInfoDto roomInfoDto:roomList){
                    // 空净
                    if("1".equals(request.getSort())){
                        if(StringUtils.isBlank(roomInfoDto.getHousingSort())){
                            if("0".equals(roomInfoDto.getClearStatus())){
                                if("0".equals(roomInfoDto.getUseStatus())){
                                    retList.add(roomInfoDto);
                                }
                            }
                        }
                    }
                    // 空脏
                    else if("2".equals(request.getSort())){
                        if(StringUtils.isBlank(roomInfoDto.getHousingSort())){
                            if("1".equals(roomInfoDto.getClearStatus())){
                                retList.add(roomInfoDto);
                            }
                        }
                    }
                    // 在住
                    else if("3".equals(request.getSort())){
                        if(StringUtils.isNotBlank(roomInfoDto.getHousingSort())){
                            retList.add(roomInfoDto);
                        }
                    }
                    // 住脏
                    else if("4".equals(request.getSort())){
                        if(StringUtils.isNotBlank(roomInfoDto.getHousingSort())){
                            if("1".equals(roomInfoDto.getClearStatus())){
                                retList.add(roomInfoDto);
                            }
                        }
                    }
                    // 空净
                    else if("5".equals(request.getSort())){
                        if(StringUtils.isNotBlank(roomInfoDto.getHousingSort())){
                            if("0".equals(roomInfoDto.getClearStatus())){
                                retList.add(roomInfoDto);
                            }
                        }
                    }
                    // 预订
                    else if("6".equals(request.getSort())){
                        if("0".equals(roomInfoDto.getBookDays())){
                            retList.add(roomInfoDto);
                        }
                    }
                    // 预离
                    else if("7".equals(request.getSort())){
                        if("1".equals(roomInfoDto.getIsTodayLeave())){
                            retList.add(roomInfoDto);
                        }
                    } // 维修
                    else if("8".equals(request.getSort())){
                        if("1".equals(roomInfoDto.getUseStatus())){
                            retList.add(roomInfoDto);
                        }
                    }
                    // 备用
                    else if("9".equals(request.getSort())){
                        if("2".equals(roomInfoDto.getUseStatus())){
                            retList.add(roomInfoDto);
                        }
                    }
                    else{
                        retList.add(roomInfoDto);
                    }
                }
                // 总房间数
                roomStatusSumDto.setAll(roomList.size());
                for(RoomInfoDto roomInfoDto:retList) {
                    if (map.get(roomInfoDto.getRoomSort()) == null) {
                        List<RoomInfoDto> list = new ArrayList<>();
                        list.add(roomInfoDto);
                        map.put(roomInfoDto.getRoomSort(), list);
                    } else {
                        List<RoomInfoDto> list = map.get(roomInfoDto.getRoomSort());
                        list.add(roomInfoDto);
                    }
                }
                for(RoomInfoDto roomInfoDto:roomList){
                    // 空净
                    if(StringUtils.isBlank(roomInfoDto.getHousingSort())){
                        if("0".equals(roomInfoDto.getClearStatus())){
                            if("0".equals(roomInfoDto.getUseStatus())){
                                roomStatusSumDto.setEmptyClean(roomStatusSumDto.getEmptyClean()+1);
                            }
                        }
                        else if("1".equals(roomInfoDto.getClearStatus())){
                            roomStatusSumDto.setEmptyDirty(roomStatusSumDto.getEmptyDirty()+1);
                        }
                    }
                    if(StringUtils.isNotBlank(roomInfoDto.getHousingSort())){
                        roomStatusSumDto.setLiveIn(roomStatusSumDto.getLiveIn()+1);
                        //住净
                        if("0".equals(roomInfoDto.getClearStatus())){
                            roomStatusSumDto.setLiveClean(roomStatusSumDto.getLiveClean()+1);
                        }
                        //住脏
                        else if("1".equals(roomInfoDto.getClearStatus())){
                            roomStatusSumDto.setLiveDirty(roomStatusSumDto.getLiveDirty()+1);
                        }
                    }
                    if("0".equals(roomInfoDto.getBookDays())){
                        roomStatusSumDto.setPreCheck(roomStatusSumDto.getPreCheck()+1);
                    }
                    if("1".equals(roomInfoDto.getIsTodayLeave())){
                        roomStatusSumDto.setPreOut(roomStatusSumDto.getPreOut()+1);
                    }
                    if("1".equals(roomInfoDto.getUseStatus())){
                        roomStatusSumDto.setRepair(roomStatusSumDto.getRepair()+1);
                    }
                    else if("2".equals(roomInfoDto.getUseStatus())){
                        roomStatusSumDto.setPrepare(roomStatusSumDto.getPrepare()+1);
                    }
                }
                // 去除无用的空列表
                for(String key:map.keySet()){
                    List list = map.get(key);
                    if(list==null||list.isEmpty()){
                        map.remove(key);
                    }
                }
            }
            roomStatusPageDto.setRoomMap(map);
            roomStatusPageDto.setRoomStatusSumDto(roomStatusSumDto);

            Double saleMoney = this.appRoomMapper.querySaleMoney();
            roomStatusPageDto.setSaleMoney(saleMoney);
            // 入住率
            RentRateDto rentRateDto = appRoomMapper.queryCurRentRate();
            DecimalFormat df = new DecimalFormat("0.##");
            String rentRate = df.format((float)rentRateDto.getRentNum()*100/rentRateDto.getRoomNum());
            roomStatusPageDto.setRentRate(rentRate);
        }
        catch(Exception e){
            logger.error("ERROR",e);
        }
        finally {
            DynamicDataSource.clear();
        }
        return roomStatusPageDto;
    }
}