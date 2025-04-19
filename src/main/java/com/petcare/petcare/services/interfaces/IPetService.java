package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.dtos.PetRequest;
import com.petcare.petcare.models.Pet;

public interface IPetService {
    public Optional<Pet> findById(Long id);
    public List<Pet> findAll();
    public List<Pet> findByOwnerId(Long id);
    public List<Pet> findByType(String type);
    public Optional<Pet> save(PetRequest request);
    public Optional<Pet> update(Long id, PetRequest request);
    public void delete(Long id);
}
