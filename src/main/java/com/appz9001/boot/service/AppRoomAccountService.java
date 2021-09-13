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

import javax.sql.DataSource;
import java.util.*;

@Service
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
            for(RoomAccountDto roomDto : roomAccountList){
                if(sortMap.containsKey(roomDto.getAccoSort())){
                    sortMap.get(roomDto.getAccoSort()).add(roomDto);
                }
                else{
                    sortMap.put(roomDto.getAccoSort(), new ArrayList<>());
                    sortMap.get(roomDto.getAccoSort()).add(roomDto);
                }
            }
            roomAccountRespDto.setRoomAccountDtoList(sortMap);
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
