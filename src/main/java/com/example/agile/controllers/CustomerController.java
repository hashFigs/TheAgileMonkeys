package com.example.agile.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
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

import com.example.agile.models.Customer;
import com.example.agile.models.CustomerDTO;
import com.example.agile.services.CustomerNotFoundException;
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
            public CustomerDTO createCustomer(@ModelAttribute Customer customer, @RequestParam("file") Optional<MultipartFile> file) throws Exception {
                return customerService.createCustomer(customer, file);
            }
        @PutMapping("/{customerId}")
            public ResponseEntity<CustomerDTO> updateCustomer(
                @PathVariable Long customerId, 
                @ModelAttribute Customer customer, 
                @RequestParam("file") MultipartFile file ) throws Exception {

                 
                 try {
                        Customer existingCustomer = customerService.getCustomer(customerId);
                        CustomerDTO custo  = customerService.updateCustomer(customerId, existingCustomer, file);
                        return ResponseEntity.ok(custo);
                    } catch (CustomerNotFoundException e) {
                        return ResponseEntity.notFound().build();
                    }
                }

        @DeleteMapping("/{customerId}")
             public void deleteCustomer(@PathVariable Long customerId) {
                customerService.deleteCustomer(customerId);
            }
    










}
