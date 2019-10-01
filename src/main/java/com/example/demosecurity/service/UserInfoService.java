package com.example.demosecurity.service;

import com.example.demosecurity.domain.UserInfo;
import com.example.demosecurity.repository.UserInfoRepository;
import com.example.demosecurity.web.errors.ErrorCode;
import com.example.demosecurity.web.errors.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * TODO
 */
@Service
@Transactional
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo findByUsername(String username){
        return userInfoRepository.findByUsername(username);
    }


    public UserInfo addUserInfo(UserInfo userInfo){
        check(userInfo);
        UserInfo byUsername = userInfoRepository.findByUsername(userInfo.getUsername());
        if (byUsername!=null){
            MyException.buildAndThrow(ErrorCode.DATA_EXIST);
        }
        return userInfoRepository.save(userInfo);
    }

    private void check(UserInfo userInfo) {
        Assert.notNull(userInfo,"数据为空");
        Assert.notNull(userInfo.getUsername(),"账号不能为空");
        Assert.notNull(userInfo.getPassword(),"密码不能为空");
    }

    public UserInfo findUserInfoByUserId(Long userid){
        return userInfoRepository.findById(userid).orElse(null);
    }
}  
