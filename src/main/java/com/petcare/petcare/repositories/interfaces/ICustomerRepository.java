package com.petcare.petcare.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.models.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> { }
