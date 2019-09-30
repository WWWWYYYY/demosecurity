package com.example.demosecurity.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {

    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;


}
