package com.userrole.myapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userrole.myapp.model.entity.User;
import com.userrole.myapp.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    // @PostMapping({"/register"})
    // public void registerNewUser(@RequestBody User user, @RequestParam Integer roleId){
    //     userService.registerNewUser(user, roleId);
    // }
    
}
