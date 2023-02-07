package com.userrole.myapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping
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
    @PostMapping
    public void registerNewUser(@RequestBody User user, @RequestParam Integer roleId){
        userService.registerNewUser(user, roleId);
    }

    //teste de acesso por role
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasAuthority('admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({ "/for1Acess" })
    @PreAuthorize("hasAuthority('TechPubs')")
    public String for1Acess() {
        return "This URL is only accessible to role with TechPubs Authority";
    }

    @GetMapping({ "/for2Acess" })
    @PreAuthorize("hasAuthority('history', 'reports')")
    public String for2Acess() {
        return "This URL is only accessible to role with history and reports  Authority";
    }

    @GetMapping({ "/for3Acess" })
    @PreAuthorize("hasAuthority('TechPubs','history', 'reports')")
    public String for3Acess() {
        return "This URL is only accessible to role with TechPubs, history and reports Authority";
    }
}
