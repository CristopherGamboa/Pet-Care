package com.petcare.petcare.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.petcare.petcare.enums.PetType;
import com.petcare.petcare.models.Pet;
import com.petcare.petcare.repositories.interfaces.IPetRepository;

@Repository
public class PetRepository implements IPetRepository {

    private final List<Pet> pets;

    public PetRepository() {
        this.pets = new ArrayList<>(List.of(
            new Pet(1L, "Buddy", PetType.CAT, 1L),
            new Pet(2L, "Milo", PetType.CAT, 2L),
            new Pet(3L, "Titi", PetType.DOG, 3L),
            new Pet(4L, "Bobby", PetType.DOG, 3L),
            new Pet(5L, "Charlie", PetType.DOG, 4L),
            new Pet(6L, "Max", PetType.CAT, 5L),
            new Pet(7L, "Rocky", PetType.CAT, 6L),
            new Pet(8L, "Coco", PetType.DOG, 7L),
            new Pet(9L, "Daisy", PetType.OTHER, 8L),
            new Pet(10L, "Shadow", PetType.OTHER, 9L)
        ));
    }

    @Override
    public List<Pet> findAll() {
        return pets;
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return pets.stream()
                   .filter(pet -> pet.getId().equals(id))
                   .findFirst();
    }

    @Override
    public List<Pet> findByOwnerId(Long ownerId) {
        return pets.stream()
                   .filter(pet -> pet.getOwnerId().equals(ownerId))
                   .collect(Collectors.toList());
    }

    @Override
    public List<Pet> findByType(String type) {
        return pets.stream()
                   .filter(pet -> pet.getPetType().equalsIgnoreCase(type))
                   .collect(Collectors.toList());
    }
}
