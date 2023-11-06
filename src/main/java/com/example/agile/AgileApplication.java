package com.example.agile;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.Role;
import com.example.agile.repository.RoleRepository;
import com.example.agile.repository.UserRepository;

@SpringBootApplication

public class AgileApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgileApplication.class, args);
	}
    
    @Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
          return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);

		  };
		}

}

