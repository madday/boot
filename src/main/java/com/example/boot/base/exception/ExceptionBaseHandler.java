package com.example.boot.base.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.boot.base.dto.ResultDto;

@RestControllerAdvice
public class ExceptionBaseHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionBaseHandler.class);

	/**
	 * 处理所有的Controller层面的异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultDto<String> handleAllExceptions(HttpServletRequest req, Exception e) {
		logger.error("未知异常", e);
		ResultDto<String> dto = new ResultDto<String>();
		dto.setCode("500");
		dto.setMessage(e.getMessage());
		return dto;
	}

	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public ResultDto<String> bizExceptionHandler(HttpServletRequest req, BizException e) {
		logger.error("业务异常:{}", e.getErrorMsg());
		return ResultDto.error(e.getErrorCode(), e.getErrorMsg());
	}
}