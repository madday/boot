package com.appz9001.boot.service;

import com.appz9001.boot.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appz9001.boot.mapper.SysUserMapper;

@Service
public class SysUserService {
	@Autowired
	private SysUserMapper testMapper;
	
	public Integer insert(SysUser domain) {
		return this.testMapper.insert(domain);
	}
}
