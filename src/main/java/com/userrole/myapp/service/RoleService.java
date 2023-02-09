package com.userrole.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userrole.myapp.model.entity.Privilege;
import com.userrole.myapp.model.entity.Role;
import com.userrole.myapp.model.entity.User;
import com.userrole.myapp.model.repository.PrivilegeRepository;
import com.userrole.myapp.model.repository.RoleRepository;
import com.userrole.myapp.model.repository.UserRepository;

@Service
public class RoleService {
    
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private PrivilegeRepository privilegeRepository;

  //List all roles
  public List<Role> findAll(){
    return roleRepository.findAll();
  }


  //change privilege on the role
  public void createRole(Role roleBody, List<Integer> ids){
    Role role = new Role();
    role.setRoleName(roleBody.getRoleName());
    role.setRoleDescription(roleBody.getRoleDescription());
    List<Privilege> privileges = privilegeRepository.findAllById(ids);
    role.setPrivileges(privileges);
    roleRepository.save(role);
  }

  //Delete (void sem return)
  public void deleteRole(Integer id){
    Role role = roleRepository.findById(id).orElse(null);
    if(role != null){
      for(User user : role.getUsers()){
        user.getRoles().remove(role);
        userRepository.save(user);
      }
      for(Privilege privilege : role.getPrivileges()){
        privilege.getRoles().remove(role);
        privilegeRepository.save(privilege);
      }
      roleRepository.deleteById(id);
    }
  }

}
