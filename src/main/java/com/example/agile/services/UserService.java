package com.example.agile.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.Customer;
import com.example.agile.models.Role;
import com.example.agile.repository.RoleRepository;
import com.example.agile.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private PasswordEncoder encoder; 

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in the user details");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
      //  return new ApplicationUser();
    }

    public List<ApplicationUser> getAllUsers() {
        return (List<ApplicationUser>) userRepository.findAll();
    }
    
    public ApplicationUser createUser(ApplicationUser user) throws Exception {
      // You can add validation and business logic here
      System.out.println("creating user");
     
      return userRepository.save(user);
    }

    public ApplicationUser getUser(Long userId) {
        Optional<ApplicationUser> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new CustomerNotFoundException("Customer with ID " + userId + " not found");

            
        }
    }

    public ApplicationUser updateUser(Long userId, ApplicationUser updatedUser) {
      // Check if the customer exists
      ApplicationUser existingUser = getUser(userId);

      // You can add validation and business logic here
      existingUser.setUsername(updatedUser.getUsername());

      return userRepository.save(existingUser);
  }


   
  @Transactional
    public ApplicationUser changeUserRole(String username, String newRole) throws Exception {
        // Retrieve the user by username
       Optional<ApplicationUser> optionalUser = userRepository.findByUsername(username);
       ApplicationUser user = optionalUser.orElseThrow(() -> new Exception("User not found with username: " + username));

            
    // Retrieve the role by authority
      Optional<Role> optionalUserRole = roleRepository.findByAuthority(newRole);
      Role userRole = optionalUserRole.orElseThrow(() -> new Exception("Role not found with role: " + newRole));

          
        
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);    

        // Update the user's role
        user.setAuthorities(authorities);

        // Save the updated user
       return userRepository.save(user);
        
    }


  
  @Transactional  
  public void deleteUser(Long userId) {
    // Check if the customer exists
    getUser(userId);

    userRepository.deleteByUserId(userId);
}


  }


    

