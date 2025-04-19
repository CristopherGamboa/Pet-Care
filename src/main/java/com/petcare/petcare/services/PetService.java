package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.dtos.PetRequest;
import com.petcare.petcare.enums.PetType;
import com.petcare.petcare.models.Pet;
import com.petcare.petcare.models.PetOwner;
import com.petcare.petcare.repositories.interfaces.IPetRepository;
import com.petcare.petcare.services.interfaces.IPetOwnerService;
import com.petcare.petcare.services.interfaces.IPetService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PetService implements IPetService {
    
    private final IPetRepository petRepository;
    private final IPetOwnerService petOwnerService;

    @Autowired
    public PetService(IPetRepository petRepository,
        IPetOwnerService petOwnerService) {
        this.petRepository = petRepository;
        this.petOwnerService = petOwnerService;
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public List<Pet> findByOwnerId(Long id) {
        return petRepository.findByOwnerId(id);
    }

    @Override
    public List<Pet> findByType(String type) {
        return petRepository.findByType(PetType.fromDisplayName(type).get());
    }

    @Override
    public Optional<Pet> save(PetRequest request) {
        PetOwner petOwner = petOwnerService.findById(request.getOwnerId())
        .orElseThrow(() -> new EntityNotFoundException("Pet owner with id " + request.getOwnerId() + " not found."));
        
        Pet pet = Pet.builder()
            .name(request.getName())
            .type(request.getType())
            .owner(petOwner)
            .build();

        return Optional.of(petRepository.save(pet));
    }

    @Override
    public Optional<Pet> update(Long id, PetRequest request) {
        if (!petRepository.existsById(id)) {
            return Optional.empty();
        }

        PetOwner petOwner = petOwnerService.findById(request.getOwnerId())
        .orElseThrow(() -> new EntityNotFoundException("Pet owner with id " + request.getOwnerId() + " not found."));
        
        Pet pet = Pet.builder()
            .id(id)
            .name(request.getName())
            .type(request.getType())
            .owner(petOwner)
            .build();

        return Optional.of(petRepository.save(pet));
    }

    @Override
    public void delete(Long id) {
        petRepository.deleteById(id);
    }
}
