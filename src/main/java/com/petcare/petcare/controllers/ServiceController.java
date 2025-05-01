package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.petcare.dtos.ServiceRequest;
import com.petcare.petcare.models.Service;
import com.petcare.petcare.services.interfaces.IServiceService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

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
    public CollectionModel<EntityModel<Service>> getAllServices() {        
        List<Service> services = serviceService.findAll(); 

        List<EntityModel<Service>> serviceModels = services.stream()
            .map(service -> EntityModel.of(service,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getServiceById(service.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServices());
        CollectionModel<EntityModel<Service>> serviceCollectionModel = CollectionModel
            .of(serviceModels, linkBuilder.withRel("services"));

        return serviceCollectionModel;
    }

    // Obtiene un servicio por id
    // ej: http://localhost:8080/api/services/1
    @GetMapping("/{id}")
    public EntityModel<Service> getServiceById(@PathVariable Long id) {
        Optional<Service> service = serviceService.findById(id);

        if (!service.isPresent()) {
            throw new EntityNotFoundException("Service not found with id: " + id);
        }

        return EntityModel.of(service.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getServiceById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServices()).withRel("services"));
    }

    @PostMapping
    public EntityModel<Service> saveService(@Valid @RequestBody ServiceRequest request) {
        Optional<Service> savedService = serviceService.save(request);

        return EntityModel.of(savedService.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getServiceById(savedService.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServices()).withRel("services"));
    }

    @PutMapping("/{id}")
    public EntityModel<Service> updateService(@PathVariable Long id, @Valid @RequestBody ServiceRequest request) {
        Optional<Service> savedService = serviceService.update(id, request);

        return EntityModel.of(savedService.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getServiceById(savedService.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServices()).withRel("services"));
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.delete(id);
    }
}
