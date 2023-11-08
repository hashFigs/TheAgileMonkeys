package com.example.agile.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agile.models.Customer;
import com.example.agile.services.CustomerService;
    

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
    
public class CustomerController {

        private final CustomerService customerService;

        public CustomerController(CustomerService customerService) {
            this.customerService = customerService;
        }
        
        @GetMapping("/")
            public List<Customer> getAllCustomers(){
            return customerService.getAllCustomers();
            //return "list of costumers";
      
        }
         @GetMapping("/{customerId}")
            public Customer getCustomer(@PathVariable Long customerId) {
            return customerService.getCustomer(customerId);
        }
         @PostMapping("/")
            public Customer createCustomer(@RequestBody Customer customer) {
            System.out.println("creating customer-controller");

            return customerService.createCustomer(customer);
        }

        @PutMapping("/{customerId}")
        public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
            return customerService.updateCustomer(customerId, customer);
        }

        @DeleteMapping("/{customerId}")
        public void deleteCustomer(@PathVariable Long customerId) {
            customerService.deleteCustomer(customerId);
        }
    










}
