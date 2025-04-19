package com.petcare.petcare.repositories.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.models.PetOwner;

public interface IPetOwnerRepository extends JpaRepository<PetOwner, Long> { 
    Optional<PetOwner> findByEmail(String email);
}
