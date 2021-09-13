package com.appz9001.boot.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.dto.*;
import com.appz9001.boot.dto.request.RoomYesterDayRequest;
import com.appz9001.boot.dto.yesterday.*;
import com.appz9001.boot.mapper.AppRoomMapper;
import com.appz9001.boot.mapper.AppRoomYesterdayMapper;
import com.appz9001.boot.util.DateUtil;
import com.appz9001.boot.util.UserUtil;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Member;
import java.math.BigDecimal;
import java.security.Principal;
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

    public RoomYesterdayDto queryRoomYesterday(RoomYesterDayRequest request) throws Exception{
        UserDetails user = UserUtil.getSysUser();
        String userId = user.getUsername();
        RoomYesterdayDto yesterdayDto = new RoomYesterdayDto();
        String sdate = request.getSdate();
        try{
            DataSource dataSource = dataSourceService.getDataSource(userId);
            DynamicDataSource.dataSourcesMap.put(userId, dataSource);
            DynamicDataSource.setDataSource(userId);

            String yesDate = appRoomMapper.querySysDateBefore();
            if(StringUtils.isBlank(sdate)){
                sdate = yesDate;
            }
            Map<String,String> params = new HashMap<>();
            params.put("start",sdate);
            params.put("end",sdate);
            // 账单信息
            List<BillInfoDto> billInfoDto = appRoomYesterdayMapper.queryBill(params);
            yesterdayDto.setBillInfoDto(billInfoDto);
            logger.info("账单信息：{}",JSON.toJSONString(billInfoDto));
            //结算情况
            SettleBillDto settleBillDto = appRoomYesterdayMapper.querySettleBill(params);
            if(settleBillDto==null){
                settleBillDto = new SettleBillDto();
            }
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
                totalSortInfo.setCiSelf(totalSortInfo.getCiSelf()+info.getCiSelf());
            }
            yesterdayDto.setTotalSortInfo(totalSortInfo);

            List<RoomStatusDto> roomStatusDtoList = this.appRoomMapper.queryRoomStatus(params);
            RoomStatusDto statusDto = new RoomStatusDto();
            if(roomStatusDtoList!=null&&!roomStatusDtoList.isEmpty()){
                statusDto = roomStatusDtoList.get(0);
                Double rentRate = new BigDecimal(statusDto.getRentRate()).multiply(new BigDecimal("100")).doubleValue();
                DecimalFormat df = new DecimalFormat("0.##");
                String str = df.format(rentRate);
                statusDto.setRentRate(str+"%");
            }
            logger.info(JSON.toJSONString(statusDto));
            yesterdayDto.setRoomStatusDto(statusDto);

            //获取会员信息
            Long memCnt = appRoomYesterdayMapper.queryMemCnt(params);
            MemberInfoDto memberInfoDto = new MemberInfoDto();
            memberInfoDto.setMemNum(memCnt);

            MemberInfoDto memDto = appRoomYesterdayMapper.queryMemInfo(params);
            if(memDto!=null){
                memberInfoDto.setMoney(memDto.getMoney());
                memberInfoDto.setFreeMoney(memDto.getFreeMoney());
            }

            memDto = appRoomYesterdayMapper.queryMemConsum(params);
            if(memDto!=null){
               memberInfoDto.setConsumeMoney(memDto.getConsumeMoney());
            }
            // 查询退费金额
            SettleBillDto retBillDto = appRoomYesterdayMapper.queryReturnMoney(params);
            settleBillDto.setFangfeiRetMoney(retBillDto.getFangfeiRetMoney());
            settleBillDto.setXiaofeiRetMoney(retBillDto.getXiaofeiRetMoney());

            yesterdayDto.setMemberInfoDto(memberInfoDto);
            CouponDto couponDto = appRoomYesterdayMapper.queryCoupon(params);
            logger.info("优惠信息：{}", JSON.toJSONString(couponDto));
            if(couponDto == null){
                couponDto = new CouponDto();
            }
            yesterdayDto.setCouponDto(couponDto);
            if(sdate.length()>=10){
                yesterdayDto.setsDate(sdate.substring(0,10));
            }
            else{
                yesterdayDto.setsDate(sdate);
            }
            yesterdayDto.setYesDate(yesDate);
        }
        catch(Exception e){
            logger.error("获取昨日信息失败",e);
        }
        finally {
            DynamicDataSource.clear();
        }
        return yesterdayDto;
    }
}
