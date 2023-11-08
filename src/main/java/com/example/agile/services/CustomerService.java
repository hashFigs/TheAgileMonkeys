package com.example.agile.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println("creating customer");
        System.out.println(customer);
        if (file != null) {
            Long mediaUploaded = mediaService.uploadMedia(file);
            customer.setPhoto(mediaUploaded);
        }

 
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        // Check if the customer exists
        Customer existingCustomer = getCustomer(customerId);

        // You can add validation and business logic here
        existingCustomer.setName(updatedCustomer.getName());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long customerId) {
        // Check if the customer exists
        getCustomer(customerId);

        customerRepository.deleteById(customerId);
    }
}