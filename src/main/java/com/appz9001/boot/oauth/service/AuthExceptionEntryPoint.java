package com.appz9001.boot.oauth.service;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.base.dto.ResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Throwable cause = authException.getCause();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if (cause instanceof InvalidTokenException) {
                ResultDto<String> resultDto = new ResultDto<>();
                resultDto.setCode("9");
                resultDto.setMessage("登录失败");
                response.getWriter().write(JSON.toJSONString(resultDto));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}