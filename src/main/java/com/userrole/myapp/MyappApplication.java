package com.userrole.myapp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.userrole.myapp.model.entity.Privilege;
import com.userrole.myapp.model.entity.Role;
import com.userrole.myapp.model.entity.User;
import com.userrole.myapp.model.repository.PrivilegeRepository;
import com.userrole.myapp.model.repository.RoleRepository;
import com.userrole.myapp.model.repository.UserRepository;

@SpringBootApplication
public class MyappApplication {

	@Bean
	public CommandLineRunner run(@Autowired UserRepository userRepository, 
								 @Autowired RoleRepository roleRepository,
								 @Autowired PrivilegeRepository privilegeRepository){
		return args -> {
			Privilege privilegeHistory = Privilege.builder().name("history").build();
			privilegeRepository.save(privilegeHistory);

			Privilege privilegeReports = Privilege.builder().name("reports").build();
			privilegeRepository.save(privilegeReports);

			Privilege privilegeTech = Privilege.builder().name("TechPubs").build();
			privilegeRepository.save(privilegeTech);

			Role role1 = new Role();
			role1.setRoleName("admin");
			role1.setRoleDescription("role admin all");
			List<Privilege> adminPrivilege = Arrays.asList(privilegeHistory, privilegeReports, privilegeTech);
			role1.setPrivileges(adminPrivilege);
			roleRepository.save(role1);

			Role role2 = new Role();
			role2.setRoleName("rep_his");
			role2.setRoleDescription("less access");
			List<Privilege> repHisPrivilege = Arrays.asList(privilegeHistory, privilegeReports);
			role2.setPrivileges(repHisPrivilege);
			roleRepository.save(role2);

			Role role3 = new Role();
			role3.setRoleName("rep_his");
			role3.setRoleDescription("restrict access");
			List<Privilege> techPrivilege = Arrays.asList(privilegeTech);
			role3.setPrivileges(techPrivilege);
			roleRepository.save(role3);

			User user1 = new User();
			user1.setUsername("rsanto");
			user1.setPassword("rafa@pass");
			user1.setRoles(Arrays.asList(role1));
			userRepository.save(user1);

			User user2 = new User();
			user2.setUsername("zeca");
			user2.setPassword("zeca@pass");
			user2.setRoles(Arrays.asList(role3));
			userRepository.save(user2);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

}
