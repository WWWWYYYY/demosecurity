package com.example.demosecurity.security;

import com.example.demosecurity.domain.UserInfo;
import com.example.demosecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *  自定义账号密码，可选择读取数据库中的账号
 */
@Component
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoService userInfoService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userinfo = userInfoService.findByUsername(username);
        if (userinfo==null){
            throw new BadCredentialsException("用户不存在");
        }
        return userinfo;
    }
}

