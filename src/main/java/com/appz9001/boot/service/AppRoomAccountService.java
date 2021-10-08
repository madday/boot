package com.appz9001.boot.service;

import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.dto.account.RoomAccountDto;
import com.appz9001.boot.dto.account.RoomAccountRespDto;
import com.appz9001.boot.dto.request.RoomStatusRequest;
import com.appz9001.boot.mapper.AppRoomMapper;
import com.appz9001.boot.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class AppRoomAccountService {
    private static final Logger logger = LoggerFactory.getLogger(AppRoomAccountService.class);
    @Autowired
    private DataSoureService dataSourceService;
    @Autowired
    private AppRoomMapper appRoomMapper;

    public RoomAccountRespDto queryRoomAccount(RoomStatusRequest request) throws Exception{
        RoomAccountRespDto roomAccountRespDto = new RoomAccountRespDto();
        UserDetails user = UserUtil.getSysUser();
        String userId = user.getUsername();
        Map<String,List<RoomAccountDto>> sortMap = new TreeMap<>();
        try{
            DataSource dataSource = dataSourceService.getDataSource(userId);
            DynamicDataSource.dataSourcesMap.put(userId, dataSource);
            DynamicDataSource.setDataSource(userId);
            List<RoomAccountDto> roomAccountList = this.appRoomMapper.queryRoomAccount();
            RoomAccountDto accountSum = new RoomAccountDto();
            accountSum.setPrepay(new BigDecimal("0"));
            accountSum.setSaleMoney(new BigDecimal("0"));
            accountSum.setBanlance(new BigDecimal("0"));
            for(RoomAccountDto roomDto : roomAccountList){
                if(sortMap.containsKey(roomDto.getAccoSort())){
                    sortMap.get(roomDto.getAccoSort()).add(roomDto);
                }
                else{
                    sortMap.put(roomDto.getAccoSort(), new ArrayList<>());
                    sortMap.get(roomDto.getAccoSort()).add(roomDto);
                }
                if(roomDto.getPrepay()!=null){
                    accountSum.setPrepay(accountSum.getPrepay().add(roomDto.getPrepay()));
                }
                if(roomDto.getSaleMoney()!=null){
                    accountSum.setSaleMoney(accountSum.getSaleMoney().add(roomDto.getSaleMoney()));
                }
                if(roomDto.getBanlance()!=null){
                    accountSum.setBanlance(accountSum.getBanlance().add(roomDto.getBanlance()));
                }
            }
            roomAccountRespDto.setRoomAccountDtoList(sortMap);
            roomAccountRespDto.setAccountSum(accountSum);
        }
        catch(Exception e){
            logger.error("ERROR",e);
        }
        finally {
            DynamicDataSource.clear();
        }
        return roomAccountRespDto;
    }
}
