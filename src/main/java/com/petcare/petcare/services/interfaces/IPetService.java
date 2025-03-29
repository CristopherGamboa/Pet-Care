package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Pet;

public interface IPetService {
    public Optional<Pet> findById(Long id);
    public List<Pet> findAll();
    public List<Pet> findByOwnerId(Long id);
    public List<Pet> findByType(String type);
}
