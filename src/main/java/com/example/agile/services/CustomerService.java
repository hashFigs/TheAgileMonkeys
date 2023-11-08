package com.example.agile.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agile.models.Customer;
import com.example.agile.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    public Customer createCustomer(Customer customer) {
        // You can add validation and business logic here
        System.out.println("creating customer");
        System.out.println(customer);
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