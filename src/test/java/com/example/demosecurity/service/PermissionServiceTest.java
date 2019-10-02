package com.example.demosecurity.service;

import com.example.demosecurity.domain.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;
    @Test
    public void addPermission() {
        Permission p = new Permission();
        p.setName("ROLE_ORDER");
        p.setAuthoritie("ROLE_ORDER");
        p.setType(0);
        permissionService.addPermission(p);

    }
}