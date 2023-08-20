package com.security.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.app.constants.AppConstants;
import com.security.app.entity.Role;
import com.security.app.service.RoleRepository;

@SpringBootApplication
public class SpringJwtSwaggerApplication implements CommandLineRunner{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJwtSwaggerApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("suresh"));
		
		try {
			Role role=new Role();
			role.setId(AppConstants.NORMAL_USER);
			role.setName("ROLE_USER");
			
			Role role1=new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");
			
			List<Role> roles = List.of(role,role1);
			List<Role> saveAll = this.roleRepository.saveAll(roles);
			saveAll.forEach(r->{
				System.out.println(r.getName());
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
