package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.dtos.PetOwnerRequest;
import com.petcare.petcare.models.PetOwner;

public interface IPetOwnerService {
    public Optional<PetOwner> findById(Long id);
    public Optional<PetOwner> findByEmail(String email);
    public List<PetOwner> findAll();
    public Optional<PetOwner> save(PetOwnerRequest request);
    public Optional<PetOwner> update(Long id, PetOwnerRequest request);
    public void delete(Long id);
}
