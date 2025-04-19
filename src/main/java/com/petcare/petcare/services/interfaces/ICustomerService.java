package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.dtos.CustomerRequest;
import com.petcare.petcare.models.Customer;

public interface ICustomerService {
    public Optional<Customer> findById(Long id);
    public List<Customer> findAll();
    public Optional<Customer> save(CustomerRequest request);
    public Optional<Customer> update(Long id, CustomerRequest request);
    public void delete(Long id);
}
