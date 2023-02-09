package com.userrole.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userrole.myapp.model.entity.Privilege;
import com.userrole.myapp.model.entity.Role;
import com.userrole.myapp.model.repository.PrivilegeRepository;
import com.userrole.myapp.model.repository.RoleRepository;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    //List all privileges
    public List<Privilege> findAll(){
        return privilegeRepository.findAll();
    }

    // Create new privilege
    public Privilege createNewPrivilege(Privilege role) {
        return privilegeRepository.save(role);
    }

    // Delete privilege (void sem return)
    public void deletePrivilege(Integer id) {
        Privilege privilege = privilegeRepository.findById(id).orElse(null);
        if(privilege != null){
            for(Role role : privilege.getRoles()){
                role.getPrivileges().remove(privilege);
                roleRepository.save(role);
            }
            privilegeRepository.deleteById(id);
        }

        // privilegeRepository.findById(id).map( priv -> {
        //     privilegeRepository.delete(priv);
        // })
    }

    
}
