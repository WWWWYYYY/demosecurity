package com.example.demosecurity.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by linziyu on 2018/5/13
 *
 * 角色实体类
 *
 */

@Data
@Entity
@Table(name = "role1")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    /**
     * 角色名
     */
    @Column(name = "rolename")
    private String rolename;

    /**
     * 对应security框架的 权限标志 作为角色权限格式为：ROLE_authoritie
     */
    @Column(name = "authoritie")
    private String authoritie;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Permission> permissions;
}
