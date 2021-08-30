package com.appz9001.boot.web;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.dto.BaseRequest;
import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.dto.HomeDto;
import com.appz9001.boot.dto.RoomInfoDto;
import com.appz9001.boot.dto.RoomStatusDto;
import com.appz9001.boot.dto.request.RoomStatusRequest;
import com.appz9001.boot.dto.request.RoomYesterDayRequest;
import com.appz9001.boot.dto.status.RoomStatusPageDto;
import com.appz9001.boot.dto.yesterday.RoomYesterdayDto;
import com.appz9001.boot.service.AppRoomService;
import com.appz9001.boot.service.AppRoomStatusService;
import com.appz9001.boot.service.AppRoomYesterdayService;
import com.appz9001.boot.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private AppRoomService roomService;
    @Autowired
    private AppRoomYesterdayService roomYesterdayService;
    @Autowired
    private AppRoomStatusService roomStatusService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @PostMapping("/queryRoomInfo")
    public ResultDto queryRoomInfo(@RequestBody BaseRequest request){
        String date = DateUtil.getYesterday();
        ResultDto dto = new ResultDto();
        HomeDto homeDto = roomService.queryIncome(request.getUserid());
        dto.setData(homeDto);
        return dto;
    }

    @PostMapping("/queryRoomYesterday")
    public ResultDto queryRoomYesterday(@RequestBody RoomYesterDayRequest request){
        ResultDto dto = new ResultDto();
        RoomYesterdayDto yesterdayDto = roomYesterdayService.queryRoomYesterday(request);
        dto.setData(yesterdayDto);
        return dto;
    }

    @PostMapping("/queryRoomStatus")
    public ResultDto queryRoomStatus(@RequestBody RoomStatusRequest request){
        ResultDto dto = new ResultDto();
        RoomStatusPageDto roomStatusPageDto = roomStatusService.queryRoomStatus(request);
        dto.setData(roomStatusPageDto);
        logger.info("房态：{}", JSON.toJSONString(roomStatusPageDto));
        return dto;
    }
}
