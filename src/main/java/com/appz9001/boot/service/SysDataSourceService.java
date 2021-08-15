package com.appz9001.boot.service;

import com.appz9001.boot.domain.SysDataSource;
import com.appz9001.boot.mapper.SysDataSourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysDataSourceService {
	@Autowired
	private SysDataSourceMapper sysDataSourceMapper;
	private static final Logger logger = LoggerFactory.getLogger(SysDataSourceService.class);

	public SysDataSource getSysDataSource(){
		return (SysDataSource) this.sysDataSourceMapper.selectByPrimaryKey("1111");
	}
}
