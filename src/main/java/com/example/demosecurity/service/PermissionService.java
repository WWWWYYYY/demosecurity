package com.example.demosecurity.service;

import com.example.demosecurity.domain.Permission;
import com.example.demosecurity.repository.PermissionRepository;
import com.example.demosecurity.web.errors.ErrorCode;
import com.example.demosecurity.web.errors.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 */
@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission addPermission(Permission permission){
        Permission p =permissionRepository.findByAuthoritie(permission.getAuthoritie());
        if (p!=null){
            MyException.buildAndThrow(ErrorCode.DATA_EXIST);
        }
        return permissionRepository.save(permission);
    }

    public Permission findByAuthoritie(String authority){
        return permissionRepository.findByAuthoritie(authority);
    }

}  
