package com.userrole.myapp.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.userrole.myapp.model.entity.Privilege;
import com.userrole.myapp.model.repository.PrivilegeRepository;
import com.userrole.myapp.service.PrivilegeService;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeRepository privilegeRepository, PrivilegeService privilegeService){
        this.privilegeRepository = privilegeRepository;
        this.privilegeService = privilegeService;
    }

    //Get all privileges
    @GetMapping
    public List<Privilege> findAll(){
        return privilegeRepository.findAll();
    }

    //Get privileges by id
    @GetMapping("{id}")
    public void findById(@PathVariable Integer id){
        privilegeService.findById(id);
    }

    //Delete new privilege
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        privilegeService.deletePrivilege(id);
    }

    @PostMapping
    public Privilege salvar(@RequestBody Privilege privilege){
        return privilegeRepository.save(privilege);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody Privilege privilege){
        privilegeRepository.findById(id).map(priv -> {
            priv.setName(privilege.getName());
            return privilegeRepository.save(priv);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilegio não encontrado"));
    }


    
}
