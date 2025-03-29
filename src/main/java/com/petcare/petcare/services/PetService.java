package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.models.Pet;
import com.petcare.petcare.repositories.interfaces.IPetRepository;
import com.petcare.petcare.services.interfaces.IPetService;

@Service
public class PetService implements IPetService {
    
    private final IPetRepository petRepository;

    @Autowired
    public PetService(IPetRepository petRepository) {
        this.petRepository = petRepository;
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
        return petRepository.findByType(type);
    }
}
