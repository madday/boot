package com.appz9001.boot.service;

import com.appz9001.boot.domain.SysUser;
import com.appz9001.boot.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appz9001.boot.mapper.SysUserMapper;

import java.sql.Connection;

@Service
public class SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);
	
	public Integer insert(SysUser domain) {
		return this.sysUserMapper.insert(domain);
	}

	public void userList() throws Exception{
		String url = "jdbc:mysql://118.195.179.140/appz9001";
		String user = "root";
		String password = "root";
		Connection connection = DBUtil.getConnection(url,user,password);
		String sql = "select count(1) cnt from sys_user";
		long count = DBUtil.getCount(connection,sql);
		logger.info("数量：{}", count);
	}

	public SysUser checkUser(String userCode,String password){
		return this.sysUserMapper.checkUser(userCode,password);
	}
}
