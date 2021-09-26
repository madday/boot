package com.appz9001.boot.web;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.dto.sysuser.ModifyPassReqDto;
import org.apache.commons.lang3.StringUtils;
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
		return sysUserService.insert(sysUser);
	}

	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody SysUser sysUser) {
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		return sysUserService.updateUser(sysUser);
	}

	@GetMapping("/delete")
	public ResultDto<Integer> add(String userCode) {
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		int result = sysUserService.deleteUser(userCode);
		resultDto.setData(result);
		return resultDto;
	}

	@PostMapping("/modifyPass")
	public ResultDto<Integer> modifyPass(@RequestBody ModifyPassReqDto reqDto) {
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		if(StringUtils.isBlank(reqDto.getUsercode())){
			resultDto.setCode("9");
			resultDto.setMessage("账号为空");
			return resultDto;
		}
		if(StringUtils.isBlank(reqDto.getPassword1())
				||StringUtils.isBlank(reqDto.getPassword2())){
			resultDto.setCode("9");
			resultDto.setMessage("新密码为空");
			return resultDto;
		}
		if(!reqDto.getPassword1().equals(reqDto.getPassword2())){
			resultDto.setCode("9");
			resultDto.setMessage("新密码不一致");
			return resultDto;
		}
		return sysUserService.modifyPass(reqDto);
	}

	@PostMapping("/list")
	public List userList() throws Exception{
		return sysUserService.userAll();
	}
}
