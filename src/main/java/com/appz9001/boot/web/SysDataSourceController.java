package com.appz9001.boot.web;

import com.appz9001.boot.base.dto.ResultDto;
import com.appz9001.boot.domain.SysDataSource;
import com.appz9001.boot.domain.SysUser;
import com.appz9001.boot.dto.sysuser.ModifyPassReqDto;
import com.appz9001.boot.service.SysDataSourceService;
import com.appz9001.boot.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysdatasource")
public class SysDataSourceController {
	@Autowired
	private SysDataSourceService sysDataSourceService;
	private static final Logger logger = LoggerFactory.getLogger(SysDataSourceController.class);

	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody SysDataSource sysDataSource) {
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		return sysDataSourceService.addDataSource(sysDataSource);
	}

	@GetMapping("/delete")
	public ResultDto<Integer> add(String dsId) {
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		int result = sysDataSourceService.deleteDataSource(dsId);
		resultDto.setData(result);
		return resultDto;
	}

	@PostMapping("/list")
	public List selectAll() throws Exception{
		return sysDataSourceService.selectAll();
	}
}
