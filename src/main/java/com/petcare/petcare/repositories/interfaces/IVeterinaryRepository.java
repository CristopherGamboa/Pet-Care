package com.petcare.petcare.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.models.Veterinary;

public interface IVeterinaryRepository extends JpaRepository<Veterinary, Long> { }
