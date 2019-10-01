package com.example.demosecurity.domain;

import lombok.Data;

import javax.persistence.*;

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

}
