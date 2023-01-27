package com.userrole.myapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userrole.myapp.model.entity.Role;
import com.userrole.myapp.model.entity.User;
import com.userrole.myapp.model.repository.RoleRepository;
import com.userrole.myapp.model.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // *tenho que passar um parametro no header do postman*
    // public User registerNewUser(User user, Integer roleId){
    //     Role roles = roleRepository.findById(roleId).get();
    //     Set<Role> userRoles = new HashSet<>();
    //     userRoles.add(roles);
    //     // user.setRole(userRoles);
    //     user.setPassword(user.getPassword());
    //     return userRepository.save(user);
    // }


    
}
