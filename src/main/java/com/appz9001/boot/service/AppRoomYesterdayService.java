package com.appz9001.boot.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.dto.*;
import com.appz9001.boot.dto.request.RoomYesterDayRequest;
import com.appz9001.boot.dto.yesterday.*;
import com.appz9001.boot.mapper.AppRoomMapper;
import com.appz9001.boot.mapper.AppRoomYesterdayMapper;
import com.appz9001.boot.util.DateUtil;
import com.appz9001.boot.util.MathUtil;
import com.appz9001.boot.util.UserUtil;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.lettuce.core.GeoArgs;
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
            List<BillInfoDto> billInfoDtoList = appRoomYesterdayMapper.queryBill(params);
            logger.info("账单信息，处理前：{}",JSON.toJSONString(billInfoDtoList));
            List<BillInfoDto> retList = dealBillList(billInfoDtoList);
            setBilShow(retList);
            yesterdayDto.setBillInfoDto(retList);
            logger.info("账单信息：{}",JSON.toJSONString(retList));
            //结算情况
            SettleBillDto settleBillDto = appRoomYesterdayMapper.querySettleBill(params);
            if(settleBillDto==null){
                settleBillDto = new SettleBillDto();
            }
            yesterdayDto.setSettleBillDto(settleBillDto);
            logger.info("结算情况：{}",JSON.toJSONString(billInfoDtoList));

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

    /**
     * 设置页面的显示信息
     * @param billInfoDtoList
     */
    private void setBilShow(List<BillInfoDto> billInfoDtoList) {
        for(BillInfoDto dto:billInfoDtoList){
            if(MathUtil.isZero(dto.getStart())){
                dto.setStartShow("--");
            }
            else{
                dto.setStartShow(dto.getStart().toPlainString());
            }
            if(MathUtil.isZero(dto.getEnd())){
                dto.setEndShow("--");
            }
            else{
                dto.setEndShow(dto.getEnd().toPlainString());
            }
            if(MathUtil.isZero(dto.getOccu())){
                dto.setOccuShow("--");
            }
            else{
                dto.setOccuShow(dto.getOccu().toPlainString());
            }
            if(MathUtil.isZero(dto.getSettle())){
                dto.setSettleShow("--");
            }
            else{
                dto.setSettleShow(dto.getSettle().toPlainString());
            }
        }
    }

    /**
     * 处理账单信息
     * @param billInfoDtoList
     * @return
     */
    private List<BillInfoDto> dealBillList(List<BillInfoDto> billInfoDtoList) {
        // 处理完返回的数据
        List<BillInfoDto> retList = new ArrayList<>();
        // sort->[dep1,dep2]
        Map<String,Set<String>> sortMap = new LinkedHashMap<>();
        // dep->[bill]
        Map<String,List<BillInfoDto>> depMap = new LinkedHashMap<>();

        for(BillInfoDto billInfoDto:billInfoDtoList){
            if(!sortMap.containsKey(billInfoDto.getSort())){
                Set<String> depSet = new LinkedHashSet<>();
                depSet.add(billInfoDto.getDep());
                sortMap.put(billInfoDto.getSort(),depSet);
            }
            else{
                sortMap.get(billInfoDto.getSort()).add(billInfoDto.getDep());
            }
        }
        BillInfoDto billInfo0 = new BillInfoDto();
        BillInfoDto billInfo1 = new BillInfoDto();
        BillInfoDto billInfo5 = new BillInfoDto();
        initBillInfo(billInfo0);
        initBillInfo(billInfo1);
        initBillInfo(billInfo5);
        for(BillInfoDto billInfoDto:billInfoDtoList){
            if(!depMap.containsKey(billInfoDto.getDep())){
                depMap.put(billInfoDto.getDep(),new ArrayList<>());
            }
            if("0".equals(billInfoDto.getSort())){
                billInfo0.setSaleSort("合计");
                billInfo0.setStart(billInfo0.getStart().add(billInfoDto.getStart()));
                billInfo0.setEnd(billInfo0.getEnd().add(billInfoDto.getEnd()));
                billInfo0.setSettle(billInfo0.getSettle().add(billInfoDto.getSettle()));
                billInfo0.setOccu(billInfo0.getOccu().add(billInfoDto.getOccu()));
                billInfo0.setType("3");
            }
            else if("1".equals(billInfoDto.getSort())){
                billInfo1.setSaleSort("合计");
                billInfo1.setStart(billInfo1.getStart().add(billInfoDto.getStart()));
                billInfo1.setEnd(billInfo1.getEnd().add(billInfoDto.getEnd()));
                billInfo1.setSettle(billInfo1.getSettle().add(billInfoDto.getSettle()));
                billInfo1.setOccu(billInfo1.getOccu().add(billInfoDto.getOccu()));
                billInfo1.setType("3");
            }
            else if("5".equals(billInfoDto.getSort())){
                billInfo5.setSaleSort("合计");
                billInfo5.setStart(billInfo5.getStart().add(billInfoDto.getStart()));
                billInfo5.setEnd(billInfo5.getEnd().add(billInfoDto.getEnd()));
                billInfo5.setSettle(billInfo5.getSettle().add(billInfoDto.getSettle()));
                billInfo5.setOccu(billInfo5.getOccu().add(billInfoDto.getOccu()));
                billInfo5.setType("3");
            }
        }

        for(String sortKey : sortMap.keySet()){
            if("0".equals(sortKey)){
                retList.add(billInfo0);
            }
            else if("1".equals(sortKey)){
                retList.add(billInfo1);
            }
            else if("5".equals(sortKey)){
                retList.add(billInfo5);
            }
            Set<String> sortSet = sortMap.get(sortKey);
            for(String key : sortSet){
                BillInfoDto depSum = new BillInfoDto();
                initBillInfo(depSum);
                for(BillInfoDto billInfoDto:billInfoDtoList){
                    if(billInfoDto.getDep().equals(key)){
                        depMap.get(key).add(billInfoDto);
                        depSum.setStart(depSum.getStart().add(billInfoDto.getStart()));
                        depSum.setEnd(depSum.getEnd().add(billInfoDto.getEnd()));
                        depSum.setSettle(depSum.getSettle().add(billInfoDto.getSettle()));
                        depSum.setOccu(depSum.getOccu().add(billInfoDto.getOccu()));
                        depSum.setSaleSort(key);
                        depSum.setType("1");
                    }
                }
                depMap.get(key).add(0,depSum);
                retList.addAll(depMap.get(key));
            }
        }
        return retList;
    }

    /**
     * 初始化账单信息
     * @param billInfoDto
     */
    private void initBillInfo(BillInfoDto billInfoDto) {
        billInfoDto.setStart(new BigDecimal("0"));
        billInfoDto.setEnd(new BigDecimal("0"));
        billInfoDto.setSettle(new BigDecimal("0"));
        billInfoDto.setOccu(new BigDecimal("0"));
        billInfoDto.setType("2");
    }
}
