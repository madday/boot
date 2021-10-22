package com.appz9001.boot.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.domain.SysDataSource;
import com.appz9001.boot.util.RedisLock;
import com.appz9001.boot.web.IndexController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisLock redisLock;

    private static final Logger logger = LoggerFactory.getLogger(DataSoureService.class);

    public synchronized DataSource getDataSource(String dsId){
        SysDataSource sysDataSource = sysDataSourceService.getSysDataSource(dsId);
        boolean exists = DynamicDataSource.containsDataSource(dsId);
        if(exists){
            logger.info("存量数据源信息：{}", DynamicDataSource.dataSourcesMap);
            HikariDataSource dataSource = (HikariDataSource)DynamicDataSource.dataSourcesMap.get(dsId);
            logger.info(dataSource.getIdleTimeout()+":time out");
            logger.info(dataSource.getMaxLifetime()+":life time");
            return dataSource;
        }
        HikariConfig config = new HikariConfig();
        config.setPoolName(dsId);
        config.setJdbcUrl(sysDataSource.getDsUrl());
        config.setUsername(sysDataSource.getDsUser());
        config.setPassword(sysDataSource.getDsPassword());
        config.setDriverClassName(sysDataSource.getDsDriver());
        config.setConnectionTestQuery("select 1");
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(120000);
        config.setMaxLifetime(180000);
        // 连接超时90秒
        config.setConnectionTimeout(60000);
        HikariDataSource dataSource = new HikariDataSource(config);
        DynamicDataSource.dataSourcesMap.put(dsId,dataSource);
        logger.info("新增数据源信息：{}", dataSource.toString());
        return dataSource;
    }
}
