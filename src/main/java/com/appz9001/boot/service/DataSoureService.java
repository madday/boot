package com.appz9001.boot.service;

import com.appz9001.boot.domain.SysDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DataSoureService {

    @Autowired
    private SysDataSourceService sysDataSourceService;

    public DataSource getDataSource(String dsId){
        SysDataSource sysDataSource = sysDataSourceService.getSysDataSource();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(sysDataSource.getDsUrl());
        dataSource.setUsername(sysDataSource.getDs_user());
        dataSource.setPassword(sysDataSource.getDs_password());
        dataSource.setDriverClassName(sysDataSource.getDs_driver());
        return dataSource;
    }
}