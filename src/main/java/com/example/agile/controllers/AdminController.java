package com.example.agile.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.LoginResponseDTO;
import com.example.agile.models.RegistrationDTO;
import com.example.agile.services.AuthenticationService;
import com.example.agile.services.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")

public class AdminController {


 private final UserService userService;
    
    public AdminController(UserService userService ){
        this.userService = userService;
    }

    
    @GetMapping("/")
    public String helloAdmin(){
        return "hi admin";
    }    

    @PostMapping("/user/change-role")
    public String changeUserRole() {
   
    System.out.println("hola");
    System.out.println("poma");

    userService.getAllUsers();
   // return userService.changeUserRole(username, newRole);
   return "hola"; 
}
}
