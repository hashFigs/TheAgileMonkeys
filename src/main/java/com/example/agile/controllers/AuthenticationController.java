package com.example.agile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.LoginResponseDTO;
import com.example.agile.models.RegistrationDTO;
import com.example.agile.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")

public class AuthenticationController {
    
    @Autowired
    private AuthenticationService  authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
        //return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }



}
