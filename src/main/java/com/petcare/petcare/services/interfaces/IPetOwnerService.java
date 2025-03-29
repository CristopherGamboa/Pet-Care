package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.PetOwner;

public interface IPetOwnerService {
    public Optional<PetOwner> findById(Long id);
    public Optional<PetOwner> findByEmail(String email);
    public List<PetOwner> findAll();
}
