package com.example.agile.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.RegistrationDTO;
import com.example.agile.models.RoleChangeDTO;
import com.example.agile.services.AuthenticationService;
import com.example.agile.services.UserNotFoundException;
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
    public ResponseEntity<ApplicationUser> updateUser(@PathVariable Long userId, @RequestBody ApplicationUser user) {
         try {
                        ApplicationUser existingUser = userService.getUser(userId);
                        ApplicationUser updatedUser  =  userService.updateUser(userId, existingUser);
                        return ResponseEntity.ok(updatedUser);
                    } catch (UserNotFoundException e) {
                        return ResponseEntity.notFound().build();
                    }
       // return userService.updateUser(userId, user);
    }
   
     @PostMapping("/change-role")
    public ApplicationUser changeUserRole(@RequestBody RoleChangeDTO body) throws Exception {
        return userService.changeUserRole(body.getUsername(), body.getNewRole());
}

}
