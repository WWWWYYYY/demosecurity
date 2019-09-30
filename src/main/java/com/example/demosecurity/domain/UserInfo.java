package com.example.demosecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * TODO
 */
@Entity
@Table(name = "tb_user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

}
