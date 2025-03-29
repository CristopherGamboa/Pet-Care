package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.petcare.models.Service;
import com.petcare.petcare.services.interfaces.IServiceService;

@RestController
@RequestMapping("api/services")
public class ServiceController {
    private final IServiceService serviceService;

    @Autowired
    public ServiceController(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Obtiene todos los servicios
    // ej: http://localhost:8080/api/services
    @GetMapping
    public List<Service> getAllServices() {        
        return serviceService.findAll();
    }

    // Obtiene un servicio por id
    // ej: http://localhost:8080/api/services/1
    @GetMapping("/{id}")
    public Optional<Service> getServiceById(@PathVariable Long id) {
        return serviceService.findById(id);
    }
}
