package com.userrole.myapp.rest.dto;

import java.util.Collection;

import com.userrole.myapp.model.entity.Privilege;
import com.userrole.myapp.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {

    private Integer id;
    private String roleName;
    private String roleDescription;
    private Collection<User> users;
    private Collection<Privilege> privileges;

}
