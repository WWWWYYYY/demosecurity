package com.example.demosecurity.service;

import com.example.demosecurity.domain.Role;
import com.example.demosecurity.repository.RoleRepository;
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
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Role addRole(Role role){
        check(role);
        Role byRolename = roleRepository.findByRolename(role.getRolename());
        if (byRolename!=null){
            MyException.buildAndThrow(ErrorCode.DATA_EXIST);
        }
        return roleRepository.save(role);
    }

    private void check(Role role) {
        Assert.notNull(role,"数据不能为空");
        Assert.notNull(role.getRolename(),"数据不能为空");
    }

    public Role findByRoleName(String rolename){
        return roleRepository.findTopByRolename(rolename);
    }
}  
