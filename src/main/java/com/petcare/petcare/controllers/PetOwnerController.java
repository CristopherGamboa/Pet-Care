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

import com.petcare.petcare.dtos.PetOwnerRequest;
import com.petcare.petcare.models.PetOwner;
import com.petcare.petcare.services.interfaces.IPetOwnerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pet-owners")
public class PetOwnerController {
    private final IPetOwnerService petOwnerService;

    @Autowired
    public PetOwnerController(IPetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    // Obtiene todos los dueños de mascotas
    // ej: http://localhost:8080/api/pet-owners
    @GetMapping
    public CollectionModel<EntityModel<PetOwner>> getAllPetOwners() {
        List<PetOwner> petOwners = petOwnerService.findAll(); 

        List<EntityModel<PetOwner>> petOwnerModels = petOwners.stream()
            .map(petOwner -> EntityModel.of(petOwner,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getPetOwnerById(petOwner.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPetOwners());
        CollectionModel<EntityModel<PetOwner>> petOwnerCollectionModel = CollectionModel
            .of(petOwnerModels, linkBuilder.withRel("petOwners"));

        return petOwnerCollectionModel;
    }

    // Obtiene un dueño de mascota por email
    // ej: http://localhost:8080/api/pet-owners/email/cristopher@example.com
    @GetMapping("/email/{email}")
    public EntityModel<PetOwner> getPetOwnerByEmail(@PathVariable String email) {
        Optional<PetOwner> petOwner = petOwnerService.findByEmail(email);

        if (!petOwner.isPresent()) {
            throw new EntityNotFoundException("PetOwner not found with email: " + email);
        }

        return EntityModel.of(petOwner.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPetOwnerById(petOwner.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPetOwners()).withRel("petOwners"));
    }

    // Obtiene un dueño de mascota por id
    // ej: http://localhost:8080/api/pet-owners/1
    @GetMapping("/{id}")
    public EntityModel<PetOwner> getPetOwnerById(@PathVariable Long id) {
        Optional<PetOwner> petOwner = petOwnerService.findById(id);

        if (!petOwner.isPresent()) {
            throw new EntityNotFoundException("PetOwner not found with id: " + id);
        }

        return EntityModel.of(petOwner.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPetOwnerById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPetOwners()).withRel("petOwners"));
    }

    @PostMapping
    public EntityModel<PetOwner> savePetOwner(@Valid @RequestBody PetOwnerRequest request) {
        Optional<PetOwner> savedPetOwner = petOwnerService.save(request);

        return EntityModel.of(savedPetOwner.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPetOwnerById(savedPetOwner.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPetOwners()).withRel("petOwners"));
    }

    @PutMapping("/{id}")
    public EntityModel<PetOwner> updatePetOwner(@PathVariable Long id, @Valid @RequestBody PetOwnerRequest request) {
        Optional<PetOwner> savedPetOwner = petOwnerService.update(id, request);

        return EntityModel.of(savedPetOwner.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPetOwnerById(savedPetOwner.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPetOwners()).withRel("petOwners"));
    }

    @DeleteMapping("/{id}")
    public void deletePetOwner(@PathVariable Long id) {
        petOwnerService.delete(id);
    }
}
