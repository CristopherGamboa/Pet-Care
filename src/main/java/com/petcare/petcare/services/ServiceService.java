package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.petcare.petcare.models.Service;
import com.petcare.petcare.repositories.interfaces.IServiceRepository;
import com.petcare.petcare.services.interfaces.IServiceService;

@org.springframework.stereotype.Service
public class ServiceService implements IServiceService {

    private final IServiceRepository serviceRepository;

    @Autowired
    public ServiceService(IServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    
}
