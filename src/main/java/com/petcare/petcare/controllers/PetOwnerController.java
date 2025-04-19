package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PetOwner> getAllPetOwners() {
        return petOwnerService.findAll();
    }

    // Obtiene un dueño de mascota por email
    // ej: http://localhost:8080/api/pet-owners/email/cristopher@example.com
    @GetMapping("/email/{email}")
    public Optional<PetOwner> getPetOwnerByEmail(@PathVariable String email) {
        return petOwnerService.findByEmail(email);
    }

    // Obtiene un dueño de mascota por id
    // ej: http://localhost:8080/api/pet-owners/1
    @GetMapping("/{id}")
    public Optional<PetOwner> getPetOwnerById(@PathVariable Long id) {
        return petOwnerService.findById(id);
    }

    @PostMapping
    public Optional<PetOwner> savePetOwner(@Valid @RequestBody PetOwnerRequest request) {
        return petOwnerService.save(request);
    }

    @PutMapping("/{id}")
    public Optional<PetOwner> updatePetOwner(@PathVariable Long id, @Valid @RequestBody PetOwnerRequest request) {
        return petOwnerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePetOwner(@PathVariable Long id) {
        petOwnerService.delete(id);
    }
}
