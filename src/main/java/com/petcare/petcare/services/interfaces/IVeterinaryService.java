package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Veterinary;

public interface IVeterinaryService {
    public Optional<Veterinary> findById(Long id);
    public List<Veterinary> findAll();
}
