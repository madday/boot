package com.appz9001.boot.web;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.domain.SysUser;
import com.appz9001.boot.service.SysUserService;
import com.appz9001.boot.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
	@Autowired
	private SysUserService sysUserService;
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/login")
	public ResultDto login(String userCode,String password) {
		ResultDto<String> dto = new ResultDto<>();
		logger.info(userCode+":"+password);
		SysUser sysUser = this.sysUserService.checkUser(userCode,password);
		if(sysUser == null){
			dto.setCode("1");
		}
		dto.setData(userCode);
		return dto;
	}
}
