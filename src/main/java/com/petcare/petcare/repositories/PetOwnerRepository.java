package com.petcare.petcare.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petcare.petcare.models.PetOwner;
import com.petcare.petcare.repositories.interfaces.IPetOwnerRepository;

@Repository
public class PetOwnerRepository implements IPetOwnerRepository {

    private List<PetOwner> petOwners;

    public PetOwnerRepository() {
        this.petOwners = new ArrayList<>(List.of(
            new PetOwner(1L, "Juan Pérez", "juan@example.com", "123456789", "Calle 123, Santiago"),
            new PetOwner(2L, "María López", "maria@example.com", "987654321", "Av. Central 456, Valparaíso"),
            new PetOwner(3L, "Cristopher Gamboa", "cristopher@example.com", "564738291", "Pasaje Los Olmos 789, Concepción"),
            new PetOwner(4L, "Ana González", "ana@example.com", "112233445", "Alameda 321, La Serena"),
            new PetOwner(5L, "Pedro Ramírez", "pedro@example.com", "998877665", "Las Palmas 567, Temuco"),
            new PetOwner(6L, "Sofía Herrera", "sofia@example.com", "776655443", "Cerro Alegre 876, Viña del Mar"),
            new PetOwner(7L, "Luis Fernández", "luis@example.com", "554433221", "Gran Avenida 432, Rancagua"),
            new PetOwner(8L, "Camila Torres", "camila@example.com", "667788990", "Ruta 68, Quilpué"),
            new PetOwner(9L, "Rodrigo Muñoz", "rodrigo@example.com", "223344556", "Los Castaños 234, Osorno"),
            new PetOwner(10L, "Valentina Rojas", "valentina@example.com", "332211445", "Costanera 678, Punta Arenas")
        ));
    }

    @Override
    public List<PetOwner> findAll() {
        return petOwners;
    }

    @Override
    public Optional<PetOwner> findByEmail(String email) {
        return petOwners.stream()
                .filter(owner -> owner.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<PetOwner> findById(Long id) {
        return petOwners.stream()
                .filter(owner -> owner.getId().equals(id))
                .findFirst();
    }
}
