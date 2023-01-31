package com.userrole.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void deteteUser(Integer id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            for(Role role : user.getRoles()){
                role.getUsers().remove(user);
                roleRepository.save(role);
            }
            userRepository.deleteById(id);
        }
    }

    public void changeUser(Integer id, User userBody, Integer roleId){

            userRepository.findById(id).map(u -> {
            u.setUsername(userBody.getUsername());
            u.setPassword((userBody.getPassword()));

            Role role = roleRepository.findById(roleId).orElse(null);
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            u.setRoles(roles);

            return userRepository.save(u);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User não encontrado"));
    }

    public void registerNewUser(User userBody, Integer roleId){
        Role role = roleRepository.findById(roleId).orElse(null);
        if(role ==null){
            userRepository.save(userBody);
        } else {
            User newUser = new User();
            newUser.setUsername(userBody.getUsername());
            // newUser.setPassword(passwordEncoder().encode(userBody.getPassword()));
            newUser.setPassword((userBody.getPassword()));

            List<Role> roles = new ArrayList<>();
            roles.add(role);
            newUser.setRoles(roles);
            userRepository.save(newUser);
        }
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

}
