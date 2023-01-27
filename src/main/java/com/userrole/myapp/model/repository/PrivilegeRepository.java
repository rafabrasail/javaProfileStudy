package com.userrole.myapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userrole.myapp.model.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    
}
