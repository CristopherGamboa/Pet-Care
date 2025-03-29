package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.models.Veterinary;
import com.petcare.petcare.repositories.interfaces.IVeterinaryRepository;
import com.petcare.petcare.services.interfaces.IVeterinaryService;

@Service
public class VeterinaryService implements IVeterinaryService {
    
    private final IVeterinaryRepository veterinaryRepository;

    @Autowired
    public VeterinaryService(IVeterinaryRepository veterinaryRepository) {
        this.veterinaryRepository = veterinaryRepository;
    }

    @Override
    public List<Veterinary> findAll() {
        return veterinaryRepository.findAll();
    }

    @Override
    public Optional<Veterinary> findById(Long id) {
        return veterinaryRepository.findById(id);
    }
}
