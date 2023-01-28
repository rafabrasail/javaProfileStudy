package com.userrole.myapp.rest.dto;

import java.util.Collection;

import com.userrole.myapp.model.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    
    private Integer id;
    private String username;
    private String password;
    private Collection<Role> roles;

}
