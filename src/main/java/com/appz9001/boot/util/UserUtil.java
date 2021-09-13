package com.appz9001.boot.util;

import com.appz9001.boot.domain.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

public class UserUtil {
    public static UserDetails getSysUser() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            return (UserDetails)principal;
        }
        else{
            throw new Exception("用户不存在!");
        }
    }
}
