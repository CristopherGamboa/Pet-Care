package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.models.PetOwner;
import com.petcare.petcare.repositories.interfaces.IPetOwnerRepository;
import com.petcare.petcare.services.interfaces.IPetOwnerService;

@Service
public class PetOwnerService implements IPetOwnerService {

    private final IPetOwnerRepository petOwnerRepository;

    @Autowired
    public PetOwnerService(IPetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public List<PetOwner> findAll() {
        return petOwnerRepository.findAll();
    }

    @Override
    public Optional<PetOwner> findByEmail(String email) {
        return petOwnerRepository.findByEmail(email);
    }

    @Override
    public Optional<PetOwner> findById(Long id) {
        return petOwnerRepository.findById(id);
    }
}
