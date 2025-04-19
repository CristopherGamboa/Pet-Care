package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.dtos.ServiceRequest;
import com.petcare.petcare.models.Service;

public interface IServiceService {
    public Optional<Service> findById(Long id); 
    public List<Service> findAll();
    public Optional<Service> save(ServiceRequest request);
    public Optional<Service> update(Long id, ServiceRequest request);
    public void delete(Long id);
}
