package com.userrole.myapp.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userrole.myapp.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);

    boolean existsByUsername(String name);
}
