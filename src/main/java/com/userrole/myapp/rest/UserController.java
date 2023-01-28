package com.userrole.myapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userrole.myapp.model.entity.User;
import com.userrole.myapp.model.repository.UserRepository;
import com.userrole.myapp.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //List all users
    @GetMapping("/all")
    public List<User> finaAll(){
        return userRepository.findAll();
    }

    //find User by Id
    @GetMapping("{id}")
    public void userById(@PathVariable Integer id){
        userRepository.findById(id);
    }

    //Delete user
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deteteUser(id);
    }

    //Change role and update from user
    @PutMapping("/{id}")
    public void changeRole(@PathVariable Integer id, @RequestBody User user, @RequestParam Integer role){
        userService.changeUser(id, user, role);
    }

    //Criar nova role
    @PostMapping({"/register"})
    public void registerNewUser(@RequestBody User user, @RequestParam Integer roleId){
        userService.registerNewUser(user, roleId);
    }
}
