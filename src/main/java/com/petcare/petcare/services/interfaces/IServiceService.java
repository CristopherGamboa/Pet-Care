package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Service;

public interface IServiceService {
    public Optional<Service> findById(Long id); 
    public List<Service> findAll();
}
