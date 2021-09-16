package com.appz9001.boot.service;

import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.domain.SysUser;
import com.appz9001.boot.dto.sysuser.ModifyPassReqDto;
import com.appz9001.boot.util.DBUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appz9001.boot.mapper.SysUserMapper;

import java.sql.Connection;
import java.util.List;

@Service
public class SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);
	
	public Integer insert(SysUser domain) {
		domain.setDsId(domain.getUserCode());
		// 有效
		domain.setStas("1");
		return this.sysUserMapper.insert(domain);
	}

	public List userAll() throws Exception{
		List<SysUser> userList = sysUserMapper.selectAll();
		for(SysUser sysUser:userList){
			if("1".equals(sysUser.getStas())){
				sysUser.setStasName("正常");
			}
			else{
				sysUser.setStasName("停用");
			}
		}
		return userList;
	}

	public SysUser checkUser(String userCode,String password){
		return this.sysUserMapper.checkUser(userCode,password);
	}

	public SysUser getUserByCode(String userCode){
		return this.sysUserMapper.getUserByCode(userCode);
	}

    public int deleteUser(String userCode) {
		return this.sysUserMapper.deleteByPrimaryKey(userCode);
    }

    public ResultDto<Integer> modifyPass(ModifyPassReqDto reqDto){
		ResultDto<Integer> resultDto = new ResultDto<>();
		SysUser sysUser = (SysUser) this.sysUserMapper.selectByPrimaryKey(reqDto.getUsercode());
		if(sysUser==null){
			resultDto.setCode("9");
			resultDto.setMessage("无此用户");
			return resultDto;
		}
		if(StringUtils.isBlank(reqDto.getPassword())){
			resultDto.setCode("9");
			resultDto.setMessage("原密码为空");
			return resultDto;
		}
		if(!reqDto.getPassword().equals(sysUser.getPassword())){
			resultDto.setCode("9");
			resultDto.setMessage("原密码不正确");
			return resultDto;
		}
		sysUser.setPassword(reqDto.getPassword1());
		int result = this.sysUserMapper.updateByPrimaryKey(sysUser);
		resultDto.setData(result);
		return resultDto;
	}
}
