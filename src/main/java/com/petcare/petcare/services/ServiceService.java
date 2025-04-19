package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.petcare.petcare.dtos.ServiceRequest;
import com.petcare.petcare.models.Service;
import com.petcare.petcare.repositories.interfaces.IServiceRepository;
import com.petcare.petcare.services.interfaces.IServiceService;

import jakarta.persistence.EntityNotFoundException;

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

    @Override
    public Optional<Service> save(ServiceRequest request) {
        Service service = Service.builder()
            .name(request.getName())
            .price(request.getPrice())
            .description(request.getDescription())
            .build();

        return Optional.of(serviceRepository.save(service));
    }

    @Override
    public Optional<Service> update(Long id, ServiceRequest request) {
        if (!serviceRepository.existsById(id)) {
            throw new EntityNotFoundException("Service with id " + id + " not found.");
        }

        Service service = Service.builder()
            .id(id)
            .name(request.getName())
            .price(request.getPrice())
            .description(request.getDescription())
            .build();


        return Optional.of(serviceRepository.save(service));
    }

    @Override
    public void delete(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new EntityNotFoundException("Service with id " + id + " not found.");
        }
        
        serviceRepository.deleteById(id);
    } 
}
