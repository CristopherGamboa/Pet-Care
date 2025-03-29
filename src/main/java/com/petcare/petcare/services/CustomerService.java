package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.models.Customer;
import com.petcare.petcare.repositories.interfaces.ICustomerRepository;
import com.petcare.petcare.services.interfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    
    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

}
