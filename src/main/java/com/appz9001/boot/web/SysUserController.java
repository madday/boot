package com.appz9001.boot.web;

import com.appz9001.boot.base.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appz9001.boot.domain.SysUser;
import com.appz9001.boot.service.SysUserService;
import com.appz9001.boot.util.StringUtil;

@RestController
@RequestMapping("/sysuser")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
	@GetMapping("/add")
	public int add() {
		logger.info("test function");
		SysUser domain = new SysUser();
		String pk = StringUtil.getUUID();
		domain.setUserCode("2");
		domain.setUserName("zhang");
		domain.setPassword("password");
		int result = sysUserService.insert(domain);
		
		return result;
	}

	@GetMapping("/login")
	public ResultDto login() {
		ResultDto<String> dto = new ResultDto<>();
		return dto;
	}
}
