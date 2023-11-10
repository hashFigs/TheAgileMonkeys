package com.example.agile.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.RoleChangeDTO;
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
    public ApplicationUser changeUserRole(@RequestBody RoleChangeDTO body) throws Exception {
        return userService.changeUserRole(body.getUsername(), body.getNewRole());
}
}
