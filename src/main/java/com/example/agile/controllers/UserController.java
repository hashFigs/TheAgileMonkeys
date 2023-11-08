package com.example.agile.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.Customer;
import com.example.agile.models.RegistrationDTO;
import com.example.agile.services.AuthenticationService;
import com.example.agile.services.CustomerService;
import com.example.agile.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")

public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    
    public UserController(UserService userService, AuthenticationService authenticationService ){
        this.userService = userService;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/")
    public List<ApplicationUser> getAllusers(){
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public ApplicationUser createUser(@RequestBody RegistrationDTO body) throws Exception {       
       // return userService.createUser(user);
       return authenticationService.registerUser(body.getUsername(), body.getPassword());

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public ApplicationUser updateUser(@PathVariable Long userId, @RequestBody ApplicationUser user) {
        return userService.updateUser(userId, user);
    }

//    @DeleteMapping("/{customerId}")
//    public void deleteCustomer(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//        }



}
