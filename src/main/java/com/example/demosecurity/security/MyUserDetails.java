package com.example.demosecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password=passwordEncoder.encode("123456");
        if ("123456".equals(username)){
            throw new BadCredentialsException("密码错误");
        }
        return new User(username,password,AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
    }
}

