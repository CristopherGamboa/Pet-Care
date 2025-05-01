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

import com.petcare.petcare.dtos.VeterinaryRequest;
import com.petcare.petcare.models.Veterinary;
import com.petcare.petcare.services.interfaces.IVeterinaryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/veterinaries")
public class VeterinaryController {
    private final IVeterinaryService veterinaryService;

    @Autowired
    public VeterinaryController(IVeterinaryService veterinaryService) {
        this.veterinaryService = veterinaryService;
    }

    // Obtiene todos los veterinarios
    // ej: http://localhost:8080/api/veterinaries
    @GetMapping
    public CollectionModel<EntityModel<Veterinary>> getAllVeterinaries() {        
        List<Veterinary> veterinaries = veterinaryService.findAll(); 

        List<EntityModel<Veterinary>> veterinaryModels = veterinaries.stream()
            .map(veterinary -> EntityModel.of(veterinary,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getVeterinaryById(veterinary.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVeterinaries());
        CollectionModel<EntityModel<Veterinary>> veterinaryCollectionModel = CollectionModel
            .of(veterinaryModels, linkBuilder.withRel("veterinaries"));

        return veterinaryCollectionModel;
    }

    // Obtiene un veterinario por id
    // ej: http://localhost:8080/api/veterinaries/1
    @GetMapping("/{id}")
    public EntityModel<Veterinary> getVeterinaryById(@PathVariable Long id) {
        Optional<Veterinary> veterinary = veterinaryService.findById(id);

        if (!veterinary.isPresent()) {
            throw new EntityNotFoundException("Veterinary not found with id: " + id);
        }

        return EntityModel.of(veterinary.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVeterinaryById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVeterinaries()).withRel("veterinaries"));
    }

    @PostMapping
    public EntityModel<Veterinary> saveVeterinary(@Valid @RequestBody VeterinaryRequest request) {
        Optional<Veterinary> savedVeterinary = veterinaryService.save(request);

        return EntityModel.of(savedVeterinary.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVeterinaryById(savedVeterinary.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVeterinaries()).withRel("veterinaries"));
    }

    @PutMapping("/{id}")
    public EntityModel<Veterinary> updateVeterinary(@PathVariable Long id, @Valid @RequestBody VeterinaryRequest request) {
        Optional<Veterinary> savedVeterinary = veterinaryService.update(id, request);

        return EntityModel.of(savedVeterinary.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVeterinaryById(savedVeterinary.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVeterinaries()).withRel("veterinaries"));
    }

    @DeleteMapping("/{id}")
    public void deleteVeterinary(@PathVariable Long id) {
        veterinaryService.delete(id);
    }
}
