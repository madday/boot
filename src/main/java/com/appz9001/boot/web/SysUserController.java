package com.appz9001.boot.web;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.appz9001.boot.domain.SysUser;
import com.appz9001.boot.service.SysUserService;
import com.appz9001.boot.util.StringUtil;

import java.util.List;

@RestController
@RequestMapping("/sysuser")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody SysUser sysUser) {
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		int result = sysUserService.insert(sysUser);
		resultDto.setData(result);
		return resultDto;
	}

	@GetMapping("/delete")
	public ResultDto<Integer> add(String userCode) {
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		int result = sysUserService.deleteUser(userCode);
		resultDto.setData(result);
		return resultDto;
	}

	@PostMapping("/list")
	public List userList() throws Exception{
		List list = sysUserService.userAll();
		logger.info(JSON.toJSONString(list));
		return list;
	}
}
