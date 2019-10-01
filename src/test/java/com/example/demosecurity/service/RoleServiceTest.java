package com.example.demosecurity.service;

import com.example.demosecurity.domain.Permission;
import com.example.demosecurity.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {


    @Autowired
    private RoleService roleService;


    @Autowired
    private PermissionService permissionService;


    @Test
    public void addRole(){
        Role role =new Role();
        role.setRolename("ADMIN");
        Permission authoritie = permissionService.findByAuthoritie("/order");
        List<Permission> ps =new ArrayList<>();
        if (authoritie!=null){
            ps.add(authoritie);
        }
        role.setPermissions(ps);
        roleService.addRole(role);
    }
}