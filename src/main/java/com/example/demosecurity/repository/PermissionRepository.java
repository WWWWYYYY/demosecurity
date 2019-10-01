package com.example.demosecurity.repository;

import com.example.demosecurity.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO
 */
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    Permission findByAuthoritie(String Authoritie);

    Permission findByName(String name);

}
