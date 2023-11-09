package com.example.agile.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import com.example.agile.models.Customer;
import com.example.agile.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MediaService mediaService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, MediaService mediaService) {
        this.customerRepository = customerRepository;
        this.mediaService = mediaService;
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer getCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");

            
        }
    }

    public Customer createCustomer(Customer customer, MultipartFile file) throws Exception {
        // You can add validation and business logic here
        System.out.println(customer);
        if (file != null) {
            Long mediaUploaded = mediaService.uploadMedia(file);
            customer.setPhoto(mediaUploaded);
           
        }

        customer.setLastUpdated(getUserIdFromRequest());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        // Check if the customer exists
        //TODO: Check if wrong customer
        Customer existingCustomer = getCustomer(customerId);

        // You can add validation and business logic here
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setName(updatedCustomer.getSurname());
        existingCustomer.setLastUpdated(getUserIdFromRequest());


        //TODO: pHOTO

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long customerId) {
        // Check if the customer exists
        getCustomer(customerId);

        customerRepository.deleteById(customerId);
    }
    
    private String getUserIdFromRequest(){
         
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
                         
             Jwt jwt = (Jwt) authentication.getPrincipal();
             return extractUserIdFromJwt(jwt);

        }

        return "none";
    }


    private String extractUserIdFromJwt(Jwt jwt) {
        // Example: Extract user ID from the "sub" claim
        Object subClaim = jwt.getClaim("sub");

        if (subClaim != null) {
            return subClaim.toString();
        }

        return "None";
    }

}