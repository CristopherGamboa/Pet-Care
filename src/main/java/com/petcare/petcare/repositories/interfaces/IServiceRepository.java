package com.petcare.petcare.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Service;

public interface IServiceRepository {
    public Optional<Service> findById(Long id); 
    public List<Service> findAll();
}
