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

import com.petcare.petcare.dtos.PetRequest;
import com.petcare.petcare.models.Pet;
import com.petcare.petcare.services.interfaces.IPetService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pets")
public class PetController {
    private final IPetService petService;

    @Autowired
    public PetController(IPetService petService) {
        this.petService = petService;
    }

    // Obtiene todas las mascotas
    // ej: localhost:8080/api/pets
    @GetMapping
    public CollectionModel<EntityModel<Pet>> getAllPets() {
        List<Pet> pets = petService.findAll(); 

        List<EntityModel<Pet>> petModels = pets.stream()
            .map(pet -> EntityModel.of(pet,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getPetById(pet.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPets());
        CollectionModel<EntityModel<Pet>> petCollectionModel = CollectionModel
            .of(petModels, linkBuilder.withRel("pets"));

        return petCollectionModel;
    }

    // Obtiene una mascota por su id
    // ej: localhost:8080/api/pets/1
    @GetMapping("/{id}")
    public EntityModel<Pet> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.findById(id);

        if (!pet.isPresent()) {
            throw new EntityNotFoundException("Pet not found with id: " + id);
        }

        return EntityModel.of(pet.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPetById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPets()).withRel("pets"));
    }

    // Obtiene las mascotas de un tipo
    // ej: localhost:8080/api/pets/type/dog
    @GetMapping("/type/{type}")
    public CollectionModel<EntityModel<Pet>> getPetsByType(@PathVariable String type) {
        List<Pet> pets = petService.findAll(); 

        List<EntityModel<Pet>> petModels = pets.stream()
            .map(pet -> EntityModel.of(pet,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getPetById(pet.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPets());
        CollectionModel<EntityModel<Pet>> petCollectionModel = CollectionModel
            .of(petModels, linkBuilder.withRel("pets"));

        return petCollectionModel;
    }

    @PostMapping
    public EntityModel<Pet> save(@Valid @RequestBody PetRequest request) {
        Optional<Pet> savedPet = petService.save(request);

        return EntityModel.of(savedPet.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPetById(savedPet.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPets()).withRel("pets"));
    }

    @PutMapping("/{id}")
    public EntityModel<Pet> update(@PathVariable Long id, @Valid @RequestBody PetRequest request) {
        Optional<Pet> savedPet = petService.update(id, request);

        return EntityModel.of(savedPet.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPetById(savedPet.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPets()).withRel("pets"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        petService.delete(id);
    }
}
