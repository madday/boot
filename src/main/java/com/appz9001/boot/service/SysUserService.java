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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;

@Service
@Transactional
public class SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);
	
	public ResultDto<Integer> insert(SysUser domain) {
		ResultDto<Integer> resultDto = new ResultDto<>();
		SysUser sysUser = (SysUser) this.sysUserMapper.selectByPrimaryKey(domain.getUserCode());
		if(sysUser != null){
			resultDto.setCode("9");
			resultDto.setMessage("该账号已存在");
			return resultDto;
		}
		domain.setDsId(domain.getUserCode());
		int result =  this.sysUserMapper.insert(domain);
		resultDto.setData(result);
		return resultDto;
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

	public ResultDto<Integer> updateUser(SysUser sysUser) {
		int result = this.sysUserMapper.updateByPrimaryKeySelective(sysUser);
		return ResultDto.success(result);
	}
}
