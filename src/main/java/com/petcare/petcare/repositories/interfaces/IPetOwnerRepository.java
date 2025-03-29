package com.petcare.petcare.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.PetOwner;

public interface IPetOwnerRepository {
    public Optional<PetOwner> findById(Long id);
    public Optional<PetOwner> findByEmail(String email);
    public List<PetOwner> findAll();
}
