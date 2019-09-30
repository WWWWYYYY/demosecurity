package com.example.demosecurity.web.rest;

import com.example.demosecurity.domain.UserInfo;
import com.example.demosecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 */
@RequestMapping("/userinfo")
@RestController
public class UserInfoResource {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public UserInfo add(@RequestBody UserInfo userInfo){
        return userInfoService.addUserInfo(userInfo);
    }

    @GetMapping("/finduserinfo/{userid}")
    public UserInfo findUserInfoByUserId(@PathVariable(value = "userid") Long userid){
        return userInfoService.findUserInfoByUserId(userid);
    }
}  
