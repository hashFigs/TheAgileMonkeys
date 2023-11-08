
package com.example.agile.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.agile.models.Customer;

@Repository

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findBySurname(String surname);
    Customer findByNameAndSurname(String name, String surname);
}

