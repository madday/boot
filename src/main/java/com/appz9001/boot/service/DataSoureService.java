package com.appz9001.boot.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.domain.SysDataSource;
import com.appz9001.boot.web.IndexController;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataSoureService {

    @Autowired
    private SysDataSourceService sysDataSourceService;

    private static final Logger logger = LoggerFactory.getLogger(DataSoureService.class);

//    private static Map<String,HikariDataSource> dsMap = new HashMap<>();

    public DataSource getDataSource(String dsId){
        SysDataSource sysDataSource = sysDataSourceService.getSysDataSource(dsId);
        String ip = sysDataSource.getDsIp();
        boolean exists = DynamicDataSource.containsDataSource(dsId);
        if(exists){
            logger.info("存量数据源信息：{}", DynamicDataSource.dataSourcesMap);
            HikariDataSource dataSource = (HikariDataSource)DynamicDataSource.dataSourcesMap.get(dsId);
            logger.info(dataSource.getIdleTimeout()+":time out");
            logger.info(dataSource.getMaxLifetime()+":life time");
            try {
                logger.info(dataSource.getLoginTimeout()+":login timeout");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return dataSource;
        }
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName(dsId);
        dataSource.setJdbcUrl(sysDataSource.getDsUrl());
        dataSource.setUsername(sysDataSource.getDsUser());
        dataSource.setPassword(sysDataSource.getDsPassword());
        dataSource.setDriverClassName(sysDataSource.getDsDriver());
        dataSource.setMaximumPoolSize(5);
        // 10s
//        dataSource.setIdleTimeout(10000);
        logger.info("新增数据源信息：{}", dataSource.toString());
        return dataSource;
    }
}
