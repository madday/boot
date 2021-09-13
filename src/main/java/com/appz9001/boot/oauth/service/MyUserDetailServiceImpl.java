package com.appz9001.boot.oauth.service;

import com.appz9001.boot.domain.SysUser;
import com.appz9001.boot.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserService sysUserService;
    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserByCode(username);
        logger.info("登录密码:{}", sysUser.getPassword());
        String password = passwordEncoder.encode(sysUser.getPassword());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
