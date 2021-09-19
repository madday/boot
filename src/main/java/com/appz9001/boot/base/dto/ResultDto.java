package com.appz9001.boot.base.dto;

import java.io.Serializable;

public class ResultDto<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code = "0"; // 默认正常码值
	private String message = "success";
	
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ResultDto<?> error(String errorCode, String errorMsg) {
		ResultDto<?> dto = new ResultDto<>();
		dto.setCode(errorCode);
		dto.setMessage(errorMsg);
		return dto;
	}
	
	public static <T> ResultDto<T> success(T data) {
		ResultDto<T> dto = new ResultDto<>();
		dto.setData(data);
		return dto;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
