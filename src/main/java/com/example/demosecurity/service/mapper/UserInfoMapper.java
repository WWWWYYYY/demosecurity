package com.example.demosecurity.service.mapper;


import com.example.demosecurity.domain.UserInfo;
import com.example.demosecurity.service.dto.UserInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserInfoMapper extends EntityMapper<UserInfoDTO,UserInfo>{

    default UserInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        return userInfo;
    }
}
