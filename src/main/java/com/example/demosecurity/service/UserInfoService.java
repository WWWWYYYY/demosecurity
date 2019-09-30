package com.example.demosecurity.service;

import com.example.demosecurity.domain.UserInfo;
import com.example.demosecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 */
@Service
@Transactional
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;




    public UserInfo addUserInfo(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }
    public UserInfo findUserInfoByUserId(Long userid){
        return userInfoRepository.findById(userid).orElse(null);
    }
}  
