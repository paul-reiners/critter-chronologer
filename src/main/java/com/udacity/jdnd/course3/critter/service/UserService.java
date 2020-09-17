package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private CustomerRepository customerRepository;

    public Long save(Customer customer) {
        customerRepository.persist(customer);

        return customer.getId();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
