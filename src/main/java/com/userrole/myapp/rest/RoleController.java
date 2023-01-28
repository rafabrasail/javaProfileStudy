package com.userrole.myapp.rest;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.userrole.myapp.model.entity.Role;
import com.userrole.myapp.model.repository.RoleRepository;
import com.userrole.myapp.service.RoleService;


@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public RoleController(RoleRepository roleRepository, RoleService roleService){
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    //Get all roles
    @GetMapping("/all")
    public Collection<Role> acharTodasRoles(){
        return roleRepository.findAll();  
    }

    //Get Role by Id
    @GetMapping("{id}")
    public void acharRolePorId(@PathVariable Integer id){
        roleRepository.findById(id);
    }

    //PUT Role only name and description
    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody Role roleBody){
        roleRepository.findById(id).map(role -> {
            role.setRoleName(roleBody.getRoleName());
            role.setRoleDescription(roleBody.getRoleDescription());
            return roleRepository.save(role);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "role não encontrado"));
    }

    //Create new role with privilege
    @PostMapping("/new")
    public void createNewRole(@RequestBody Role roleBody, @RequestParam List<Integer> ids){
        roleService.createRole(roleBody, ids);
    }

    //Delete role
    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable Integer id){
        roleService.deleteRole(id);
    }

    
}
