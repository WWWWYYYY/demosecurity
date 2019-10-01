package com.example.demosecurity.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * TODO
 */
@Data
@Entity
@Table(name = "tb_permission")
public class Permission {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    /**
     * 权限名
     */
    @Column(name = "name")
    private String name;

    /**
     * 对应security框架的 权限标志
     */
    @Column(name = "authoritie")
    private String authoritie;

    /**
     * 权限类型
     */
    @Column(name = "type")
    private Integer type;
}  
