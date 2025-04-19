package com.petcare.petcare.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.models.Service;

public interface IServiceRepository extends JpaRepository<Service, Long> { }
