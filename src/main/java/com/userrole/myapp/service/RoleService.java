package com.userrole.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userrole.myapp.model.entity.Role;
import com.userrole.myapp.model.repository.RoleRepository;

@Service
public class RoleService {
    
  @Autowired
  private RoleRepository roleRepository;

  //List all roles
  public List<Role> findAll(){
    return roleRepository.findAll();
  }

  //Finda by id
  public Role findById(Integer id){
    return roleRepository.findById(id).get();
  }

  //Create new user
  public Role createNewRole(Role role){
    return roleRepository.save(role);
  }

  //Delete (void sem return)
  public void deleteRole(Integer id){
    roleRepository.deleteById(id);
  }


}
