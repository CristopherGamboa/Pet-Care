package com.petcare.petcare.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Customer;

public interface ICustomerRepository {
    public Optional<Customer> findById(Long id);
    public List<Customer> findAll();
}
