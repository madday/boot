package com.appz9001.boot.web;

import com.appz9001.boot.base.dto.BaseRequest;
import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.dto.HomeDto;
import com.appz9001.boot.dto.yesterday.RoomYesterdayDto;
import com.appz9001.boot.service.AppRoomService;
import com.appz9001.boot.service.AppRoomYesterdayService;
import com.appz9001.boot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/queryRoomInfo")
    public ResultDto queryRoomInfo(@RequestBody BaseRequest request){
        String date = DateUtil.getYesterday();
        ResultDto dto = new ResultDto();
        HomeDto homeDto = roomService.queryIncome(date,request.getUserid());
        dto.setData(homeDto);
        return dto;
    }

    @PostMapping("/queryRoomYesterday")
    public ResultDto queryRoomYesterday(@RequestBody BaseRequest request){
        String date = DateUtil.getYesterday();
        ResultDto dto = new ResultDto();
        RoomYesterdayDto yesterdayDto = roomYesterdayService.queryRoomYesterday(date,request.getUserid());
        dto.setData(yesterdayDto);
        return dto;
    }
}
