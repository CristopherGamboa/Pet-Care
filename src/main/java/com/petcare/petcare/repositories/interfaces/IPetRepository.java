package com.petcare.petcare.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.enums.PetType;
import com.petcare.petcare.models.Pet;

public interface IPetRepository extends JpaRepository<Pet, Long> { 
    List<Pet> findByOwnerId(Long id);
    List<Pet> findByType(PetType type);
}
