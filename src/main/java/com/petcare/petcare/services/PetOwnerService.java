package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.dtos.PetOwnerRequest;
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

    @Override
    public Optional<PetOwner> save(PetOwnerRequest request) {
        PetOwner petOwner = PetOwner.builder()
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();

        return Optional.of(petOwnerRepository.save(petOwner));
    }

    @Override
    public Optional<PetOwner> update(Long id, PetOwnerRequest request) {
        if (!petOwnerRepository.existsById(id)) {
            return Optional.empty();
        }

        PetOwner petOwner = PetOwner.builder()
            .id(id)
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();

        return Optional.of(petOwnerRepository.save(petOwner));
    }

    @Override
    public void delete(Long id) {
        petOwnerRepository.deleteById(id);
    }
}
