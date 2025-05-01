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

import com.petcare.petcare.dtos.CustomerRequest;
import com.petcare.petcare.models.Customer;
import com.petcare.petcare.services.interfaces.ICustomerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }   

    // Obtiene todos los clientes
    // ej: http://localhost:8080/api/customers
    @GetMapping
    public CollectionModel<EntityModel<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll(); 

        List<EntityModel<Customer>> customerModels = customers.stream()
            .map(customer -> EntityModel.of(customer,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getCustomerById(customer.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCustomers());
        CollectionModel<EntityModel<Customer>> customerCollectionModel = CollectionModel
            .of(customerModels, linkBuilder.withRel("customers"));

        return customerCollectionModel;
    }

    // Obtiene un cliente por id
    // ej: http://localhost:8080/api/customers/3
    @GetMapping("/{id}")
    public EntityModel<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);

        if (!customer.isPresent()) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }

        return EntityModel.of(customer.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCustomers()).withRel("customers"));
    }

    @PostMapping
    public EntityModel<Customer> saveCustomer(@Valid @RequestBody CustomerRequest request) {
        Optional<Customer> savedCustomer = customerService.save(request);

        return EntityModel.of(savedCustomer.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerById(savedCustomer.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCustomers()).withRel("customers"));
    }

    @PutMapping("/{id}")
    public EntityModel<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        Optional<Customer> savedCustomer = customerService.update(id, request);

        return EntityModel.of(savedCustomer.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerById(savedCustomer.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCustomers()).withRel("customers"));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
    }
}
