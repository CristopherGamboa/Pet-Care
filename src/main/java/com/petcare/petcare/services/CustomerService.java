package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.dtos.CustomerRequest;
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

    @Override
    public Optional<Customer> save(CustomerRequest request) {
        Customer customer = Customer.builder()
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();

        return Optional.of(customerRepository.save(customer));
    }

    @Override
    public Optional<Customer> update(Long id, CustomerRequest request) {
        if(!customerRepository.existsById(id)){
            return Optional.empty();
        }

        Customer customer = Customer.builder()
            .id(id)
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();

        return Optional.of(customerRepository.save(customer));
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

}
