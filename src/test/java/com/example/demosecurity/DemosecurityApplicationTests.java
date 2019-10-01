package com.example.demosecurity;

import com.example.demosecurity.domain.Role;
import com.example.demosecurity.domain.UserInfo;
import com.example.demosecurity.service.RoleService;
import com.example.demosecurity.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemosecurityApplicationTests {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addRole(){
        Role role =new Role();
        role.setRolename("ADMIN");
        roleService.addRole(role);
    }

    @Test
    public void addUserInfo(){
        UserInfo userInfo =new UserInfo();
        userInfo.setUsername("admin");
        String password=passwordEncoder.encode("123456");
        userInfo.setPassword(password);
        List<Role> roleList =new ArrayList<>();
        roleList.add(roleService.findByRoleName("ADMIN"));
        userInfo.setRoles(roleList);
        userInfoService.addUserInfo(userInfo);
    }

}
