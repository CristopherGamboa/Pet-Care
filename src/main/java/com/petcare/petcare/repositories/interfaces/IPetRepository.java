package com.petcare.petcare.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Pet;

public interface IPetRepository {
    public Optional<Pet> findById(Long id);
    public List<Pet> findAll();
    public List<Pet> findByOwnerId(Long id);
    public List<Pet> findByType(String type);
}
