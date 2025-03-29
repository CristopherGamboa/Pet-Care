package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.petcare.models.Veterinary;
import com.petcare.petcare.services.interfaces.IVeterinaryService;

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
}
