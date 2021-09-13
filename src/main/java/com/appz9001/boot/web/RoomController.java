package com.appz9001.boot.web;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.dto.BaseRequest;
import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.dto.HomeDto;
import com.appz9001.boot.dto.WeekRateDto;
import com.appz9001.boot.dto.account.RoomAccountRespDto;
import com.appz9001.boot.dto.request.RoomStatusRequest;
import com.appz9001.boot.dto.request.RoomYesterDayRequest;
import com.appz9001.boot.dto.status.RoomStatusPageDto;
import com.appz9001.boot.dto.yesterday.RoomYesterdayDto;
import com.appz9001.boot.service.AppRoomAccountService;
import com.appz9001.boot.service.AppRoomService;
import com.appz9001.boot.service.AppRoomStatusService;
import com.appz9001.boot.service.AppRoomYesterdayService;
import com.appz9001.boot.util.DateUtil;
import com.appz9001.boot.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private AppRoomService roomService;
    @Autowired
    private AppRoomYesterdayService roomYesterdayService;
    @Autowired
    private AppRoomStatusService roomStatusService;
    @Autowired
    private AppRoomAccountService roomAccountService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @PostMapping("/queryRoomInfo")
    public ResultDto<HomeDto> queryRoomInfo(@RequestBody BaseRequest request) throws Exception{
        String date = DateUtil.getYesterday();
        ResultDto<HomeDto> dto = new ResultDto<>();
        UserDetails user = UserUtil.getSysUser();
        String userId = user.getUsername();
        HomeDto homeDto = roomService.queryIncome(userId);
        dto.setData(homeDto);
        return dto;
    }

    @PostMapping("/queryWeekInfo")
    public ResultDto<WeekRateDto> queryWeekInfo(@RequestBody BaseRequest request) throws Exception{
        ResultDto<WeekRateDto> dto = new ResultDto<>();
        WeekRateDto weekRateDto = roomService.queryWeekInfo(request);
        dto.setData(weekRateDto);
        return dto;
    }

    @PostMapping("/queryRoomYesterday")
    public ResultDto<RoomYesterdayDto> queryRoomYesterday(@RequestBody RoomYesterDayRequest request) throws Exception{
        ResultDto<RoomYesterdayDto> dto = new ResultDto<>();
        RoomYesterdayDto yesterdayDto = roomYesterdayService.queryRoomYesterday(request);
        dto.setData(yesterdayDto);
        return dto;
    }

    @PostMapping("/queryRoomStatus")
    public ResultDto<RoomStatusPageDto> queryRoomStatus(@RequestBody RoomStatusRequest request) throws Exception{
        ResultDto<RoomStatusPageDto> dto = new ResultDto<>();
        RoomStatusPageDto roomStatusPageDto = roomStatusService.queryRoomStatus(request);
        dto.setData(roomStatusPageDto);
        logger.info("房态：{}", JSON.toJSONString(roomStatusPageDto));
        return dto;
    }

    @PostMapping("/queryRoomAccount")
    public ResultDto<RoomAccountRespDto> queryRoomAccount(@RequestBody RoomStatusRequest request) throws Exception{
        ResultDto<RoomAccountRespDto> dto = new ResultDto<>();
        RoomAccountRespDto roomAccountRespDto = roomAccountService.queryRoomAccount(request);
        dto.setData(roomAccountRespDto);
        logger.info("账户一览：{}", JSON.toJSONString(roomAccountRespDto));
        return dto;
    }
}
