package com.example.agile.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import com.example.agile.models.Customer;
import com.example.agile.models.CustomerDTO;
import com.example.agile.models.Media;
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

    public CustomerDTO createCustomer(Customer customer, MultipartFile file) throws Exception {
        // You can add validation and business logic here
        System.out.println(customer);
        CustomerDTO customerDTO = new CustomerDTO();
        
        
        if (file != null) {
            Media mediaUploaded = mediaService.uploadMedia(file);
            customer.setPhoto(mediaUploaded.getId());
            customerDTO.setMediaId(mediaUploaded.getId());
            customerDTO.setPhotoUrl(mediaUploaded.getFileName());

        }
        
        customer.setLastUpdated(getUserIdFromRequest());
        customerRepository.save(customer);


        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setName(customer.getName());
        customerDTO.setLastUpdatedBy(customer.getLastUpdated());


        return customerDTO;
    }

    public CustomerDTO updateCustomer(Long customerId, Customer updatedCustomer, MultipartFile file) throws Exception {
        // Check if the customer exists
        //TODO: Check if wrong customer
        Customer existingCustomer = getCustomer(customerId);
        CustomerDTO customerDTO = new CustomerDTO();
 
         if (file != null) {
            Media mediaUploaded = mediaService.uploadMedia(file);
            existingCustomer.setPhoto(mediaUploaded.getId());
            customerDTO.setMediaId(mediaUploaded.getId());
            customerDTO.setPhotoUrl(mediaUploaded.getFileName());

        }
        
         // You can add validation and business logic here
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setSurname(updatedCustomer.getSurname());
        existingCustomer.setLastUpdated(getUserIdFromRequest());
        customerRepository.save(existingCustomer);

        customerDTO.setId(existingCustomer.getId());
        customerDTO.setName(existingCustomer.getName());
        customerDTO.setSurname(existingCustomer.getSurname());
        customerDTO.setName(existingCustomer.getName());
        customerDTO.setLastUpdatedBy(existingCustomer.getLastUpdated());
       

        return  customerDTO;
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