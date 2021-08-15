package com.appz9001.boot.mapper;

import com.appz9001.boot.base.mapper.BaseMapper;
import com.appz9001.boot.domain.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {
	public SysUser checkUser(@Param("userCode") String userCode, @Param("password") String password);
}
