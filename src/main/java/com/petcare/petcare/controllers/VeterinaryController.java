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

import com.petcare.petcare.dtos.VeterinaryRequest;
import com.petcare.petcare.models.Veterinary;
import com.petcare.petcare.services.interfaces.IVeterinaryService;

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
    public List<Veterinary> getAllVeterinaries() {        
        return veterinaryService.findAll();
    }

    // Obtiene un veterinario por id
    // ej: http://localhost:8080/api/veterinaries/1
    @GetMapping("/{id}")
    public Optional<Veterinary> getVeterinaryById(@PathVariable Long id) {
        return veterinaryService.findById(id);
    }

    @PostMapping
    public Optional<Veterinary> saveVeterinary(@Valid @RequestBody VeterinaryRequest request) {
        return veterinaryService.save(request);
    }

    @PutMapping("/{id}")
    public Optional<Veterinary> updateVeterinary(@PathVariable Long id, @Valid @RequestBody VeterinaryRequest request) {
        return veterinaryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteVeterinary(@PathVariable Long id) {
        veterinaryService.delete(id);
    }
}
