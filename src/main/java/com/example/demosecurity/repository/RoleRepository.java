package com.example.demosecurity.repository;

import com.example.demosecurity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findTopByRolename(String rolename);
    Role findByRolename(String rolename);
}
