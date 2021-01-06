package com.example.boot.base.dto;

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

	public static ResultDto<String> error(String errorCode, String errorMsg) {
		ResultDto<String> dto = new ResultDto<>();
		dto.setCode(errorCode);
		dto.setMessage(errorMsg);
		return dto;
	}
	
	public static ResultDto<String> success(String errorMsg) {
		ResultDto<String> dto = new ResultDto<>();
		dto.setMessage(errorMsg);
		return dto;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
