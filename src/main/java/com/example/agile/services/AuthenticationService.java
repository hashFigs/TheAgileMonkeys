package com.example.agile.services;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.LoginResponseDTO;
import com.example.agile.models.Role;
import com.example.agile.repository.RoleRepository;
import com.example.agile.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("ADMIN").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }
   

    public LoginResponseDTO loginUser(String username, String password) {
     try{   
         
        Authentication auth = authenticationManager.authenticate( 
            new UsernamePasswordAuthenticationToken(username,  password)
        );
        
        String token = tokenService.generateJwt(auth);
        
        
        return new LoginResponseDTO(userRepository.findByUsername(username).get(), token );

    }catch(Exception e){
        System.out.println("Authentication failed: " + e.getMessage());
        return new LoginResponseDTO(null, "");
      }
    }


}
