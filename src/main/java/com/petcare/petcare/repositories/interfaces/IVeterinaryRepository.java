package com.petcare.petcare.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Veterinary;

public interface IVeterinaryRepository {
    public Optional<Veterinary> findById(Long id);
    public List<Veterinary> findAll();
}
