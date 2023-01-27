package com.userrole.myapp.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userrole.myapp.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String name);
}
