package com.appz9001.boot.service;

import com.appz9001.boot.base.DynamicDataSource;
import com.appz9001.boot.base.constant.SysConstant;
import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.domain.SysDataSource;
import com.appz9001.boot.mapper.SysDataSourceMapper;
import com.appz9001.boot.util.DBUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.test.TestAccounts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;


@Service
@Transactional
public class SysDataSourceService {
	@Autowired
	private SysDataSourceMapper sysDataSourceMapper;
	private static final Logger logger = LoggerFactory.getLogger(SysDataSourceService.class);

	public SysDataSource getSysDataSource(String dsId){
		return (SysDataSource) this.sysDataSourceMapper.selectByPrimaryKey(dsId);
	}

	public ResultDto<Integer> addDataSource(SysDataSource sysDataSource){
		String dsId = sysDataSource.getDsId();
		SysDataSource dataSourceQuery = (SysDataSource) sysDataSourceMapper.selectByPrimaryKey(dsId);
		if(dataSourceQuery != null){
			ResultDto.error("9","该编号已存在");
		}
		sysDataSource.setDsDriver(SysConstant.SQL_SERVER_DRIVER);
		sysDataSource.setDsUrl(SysConstant.SQL_SERVER_URL_PREFIX+sysDataSource.getDsIp()+SysConstant.SQL_SERVER_URL_SUFFIX);
		boolean flag = testDataSource(sysDataSource);
		if(flag){
			int result = this.sysDataSourceMapper.insertSelective(sysDataSource);
			return ResultDto.success(result);
		}
		else{
			ResultDto<Integer> resultDto = new ResultDto<>();
			resultDto.setCode("9");
			resultDto.setMessage("数据源连接失败");
			return resultDto;
		}
	}

	public int deleteDataSource(String dsId){
		int result = this.sysDataSourceMapper.deleteByPrimaryKey(dsId);
		clearDateSource(dsId);
		return result;
	}

	public List<SysDataSource> selectAll(){
		return this.sysDataSourceMapper.selectAll();
	}

    public ResultDto<Integer> updateDataSource(SysDataSource sysDataSource) {
		sysDataSource.setDsUrl(SysConstant.SQL_SERVER_URL_PREFIX+sysDataSource.getDsIp()+SysConstant.SQL_SERVER_URL_SUFFIX);
		boolean flag = testDataSource(sysDataSource);
		if(flag){
			clearDateSource(sysDataSource.getDsId());
			int result = this.sysDataSourceMapper.updateByPrimaryKeySelective(sysDataSource);
			return ResultDto.success(result);
		}
		else{
			ResultDto<Integer> resultDto = new ResultDto<>();
			resultDto.setCode("9");
			resultDto.setMessage("数据源连接失败");
			return resultDto;
		}
    }

    private boolean testDataSource(SysDataSource sysDataSource){
//		try{
//			Connection conn = DBUtil.getConnection(sysDataSource.getDsUrl(),sysDataSource.getDsUser(),sysDataSource.getDsPassword());
//			if(conn == null){
//				return false;
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

    private void clearDateSource(String userId){
		boolean exists = DynamicDataSource.containsDataSource(userId);
		if(exists){
			logger.info("清除数据源信息：{}", userId);
			HikariDataSource dataSource = (HikariDataSource) DynamicDataSource.dataSourcesMap.get(userId);
			if(dataSource != null){
				try{
					dataSource.close();
				}
				catch(Exception e){
					logger.error("close datasource exception", e);
				}
				DynamicDataSource.dataSourcesMap.remove(userId);
			}
		}
	}
}
