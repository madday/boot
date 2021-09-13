package com.appz9001.boot.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.base.dto.BaseRequest;
import com.appz9001.boot.dto.*;
import com.appz9001.boot.mapper.AppRoomMapper;
import com.appz9001.boot.util.DateUtil;
import com.appz9001.boot.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class AppRoomService {
    private static final Logger logger = LoggerFactory.getLogger(AppRoomService.class);
    @Autowired
    private DataSoureService dataSourceService;
    @Autowired
    private AppRoomMapper appRoomMapper;

    public HomeDto queryIncome(String userId){
        HomeDto homeDto = new HomeDto();
        try{
            DataSource dataSource = dataSourceService.getDataSource(userId);
            DynamicDataSource.dataSourcesMap.put(userId, dataSource);
            DynamicDataSource.setDataSource(userId);
            String date = appRoomMapper.querySysDate();
            String yesterday = appRoomMapper.querySysDateBefore();
            logger.info("系统日期：{}",date);
            //收入
            IncomeDto incomeDto = this.buildIncomeInfo(yesterday);
            homeDto.setIncomeDto(incomeDto);
            // 出租率
            RentRateDto rentRateDto = appRoomMapper.queryCurRentRate();
            logger.info("出租信息:{}",JSON.toJSONString(rentRateDto));
            DecimalFormat df = new DecimalFormat("0.##");
            String rentRate = df.format((float)rentRateDto.getRentNum()*100/rentRateDto.getRoomNum());
            rentRateDto.setRentRate(rentRate);
            homeDto.setRentRateDto(rentRateDto);
            // 房态汇总
            RoomSumInfo roomSumInfo = this.buildRoomSumInfo();
            homeDto.setRoomSumInfo(roomSumInfo);
            // 获取一星期七天的出租率
            logger.info("首页信息:{}",JSON.toJSONString(homeDto));
//            WeekRateDto weekRateDto = this.buildWeekRentRate();
//            homeDto.setWeekRateDto(weekRateDto);
            //入住信息
            CheckInfoDto checkInfoDto = this.buildCheckInfo();
            homeDto.setCheckInfoDto(checkInfoDto);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            DynamicDataSource.clear();
        }
        return homeDto;
    }

    private CheckInfoDto buildCheckInfo() {
       CheckInfoDto checkInfoDto =  this.appRoomMapper.queryCheckInfo();
       return checkInfoDto;
    }

    private IncomeDto buildIncomeInfo(String date) {
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setDayMoney(0d);
        incomeDto.setMonthMoney(0d);
        incomeDto.setYearMoney(0d);
        Map<String,String> param = new HashMap<>();
        param.put("date",date);
        List<IncomeDto> incomeList = appRoomMapper.queryIncome(param);
        for(IncomeDto dto:incomeList){
            incomeDto.setDayMoney(dto.getDayMoney()+incomeDto.getDayMoney());
            incomeDto.setMonthMoney(dto.getMonthMoney()+incomeDto.getMonthMoney());
            incomeDto.setYearMoney(dto.getYearMoney()+incomeDto.getYearMoney());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        logger.info("营业收入信息:{}", JSON.toJSONString(incomeDto));
        incomeDto.setDayIncome(decimalFormat.format(incomeDto.getDayMoney()));
        incomeDto.setMonthIncome(decimalFormat.format(incomeDto.getMonthMoney()));
        incomeDto.setYearIncome(decimalFormat.format(incomeDto.getYearMoney()));
        return incomeDto;
    }


    public RoomSumInfo buildRoomSumInfo(){
        List<RoomInfoDto> roomInfoList = appRoomMapper.queryRoomInfo();
        RoomSumInfo roomSumInfo = new RoomSumInfo();
        // 总房间
        roomSumInfo.setRoomNum(roomInfoList.size());
        for(RoomInfoDto roomInfoDto:roomInfoList){
            if("1".equals(roomInfoDto.getIsTodayLeave())){
                // 将离
                roomSumInfo.setRoomLeave(roomSumInfo.getRoomLeave()+1);
            }
            if("1".equals(roomInfoDto.getHousingSort())){
                // 钟点
                roomSumInfo.setRoomTime(roomSumInfo.getRoomTime()+1);
            }
            else if("0".equals(roomInfoDto.getHousingSort())){
                // 全天
                roomSumInfo.setRoomWholeDay(roomSumInfo.getRoomWholeDay()+1);
            }
            else if("2".equals(roomInfoDto.getHousingSort())){
                // 自用
                roomSumInfo.setRoomSelf(roomSumInfo.getRoomSelf()+1);
            }
            if(StringUtils.isNotBlank(roomInfoDto.getCheckinTime())){
                // 在住
                roomSumInfo.setRoomIn(roomSumInfo.getRoomIn()+1);
            }
            // 预定
            String bookDays = roomInfoDto.getBookDays();
            if(StringUtils.isNotBlank(bookDays)&&Integer.parseInt(bookDays)>=0){
                roomSumInfo.setRoomBook(roomSumInfo.getRoomBook()+1);
            }
            // 空房
            if(StringUtils.isBlank(roomInfoDto.getHousingSort())){
                if("0".equals(roomInfoDto.getClearStatus())){
                    if("0".equals(roomInfoDto.getUseStatus())){
                        // 空净
                        roomSumInfo.setRoomClean(roomSumInfo.getRoomClean()+1);
                    }
                }
                if("1".equals(roomInfoDto.getClearStatus())){
                    // 空脏
                    roomSumInfo.setRoomDirty(roomSumInfo.getRoomDirty()+1);
                }
            }
            //维修
            if("1".equals(roomInfoDto.getUseStatus())){
                roomSumInfo.setRoomRepair(roomSumInfo.getRoomRepair()+1);
            }
            //备用
            else if("2".equals(roomInfoDto.getUseStatus())){
                roomSumInfo.setRoomPrepare(roomSumInfo.getRoomPrepare()+1);
            }
        }
        return roomSumInfo;
    }

    private WeekRateDto buildWeekRentRate(){
        String end = this.appRoomMapper.querySysDateBefore();
        String endStr = end.substring(0,10);
        String start = DateUtil.dateBefore(endStr,-6);
        Map<String,String> param = new HashMap<>();
        param.put("start",start);
        param.put("end",endStr);
        List<RoomStatusDto> rateList = this.appRoomMapper.queryRoomStatus(param);
        List<String> weekRList = new ArrayList<>();
        for(RoomStatusDto dto:rateList){
            Double rentRate = new BigDecimal(dto.getRentRate()).multiply(new BigDecimal("100")).doubleValue();
            DecimalFormat df = new DecimalFormat("0.##");
            String str = df.format(rentRate);
            dto.setRentRate(str);
            weekRList.add(str);
        }
        logger.info(JSON.toJSONString(weekRList));
        WeekRateDto weekRateDto = new WeekRateDto();
        weekRateDto.setDataList(weekRList);
        weekRateDto.setWeekList(DateUtil.getWeekList());
        return weekRateDto;
    }

    public WeekRateDto queryWeekInfo(BaseRequest request) throws Exception {
        UserDetails user = UserUtil.getSysUser();
        String userId = user.getUsername();
        WeekRateDto weekRateDto = new WeekRateDto();
        try{
            DataSource dataSource = dataSourceService.getDataSource(userId);
            DynamicDataSource.dataSourcesMap.put(userId, dataSource);
            DynamicDataSource.setDataSource(userId);
            weekRateDto = this.buildWeekRentRate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            DynamicDataSource.clear();
        }
        return weekRateDto;
    }
}
