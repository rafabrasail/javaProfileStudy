package com.userrole.myapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userrole.myapp.model.entity.Privilege;
import com.userrole.myapp.model.entity.Role;
import com.userrole.myapp.model.entity.User;
import com.userrole.myapp.model.repository.RoleRepository;
import com.userrole.myapp.model.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(), getAuthorities(user.getRoles())
            );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles){
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        
        for(Role role : roles){
            privileges.add(role.getRoleName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection){
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    
}
