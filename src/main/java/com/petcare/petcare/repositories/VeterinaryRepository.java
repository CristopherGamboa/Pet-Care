package com.petcare.petcare.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petcare.petcare.models.Veterinary;
import com.petcare.petcare.repositories.interfaces.IVeterinaryRepository;

@Repository
public class VeterinaryRepository implements IVeterinaryRepository {

    private List<Veterinary> veterinaries;

    public VeterinaryRepository() {
        veterinaries = new ArrayList<>(List.of(
            new Veterinary(1L, "Dr. John Doe", "johndoe@vet.com", "123456789", "123 Vet St."),
            new Veterinary(2L, "Dr. Jane Smith", "janesmith@vet.com", "987654321", "456 Animal Ave."),
            new Veterinary(3L, "Dr. Emily Clark", "emilyclark@vet.com", "564738291", "789 Care Rd."),
            new Veterinary(4L, "Dr. Michael Johnson", "michaeljohnson@vet.com", "657483920", "101 Pet Blvd."),
            new Veterinary(5L, "Dr. Sarah Lee", "sarahlee@vet.com", "102938475", "202 Pet Place."),
            new Veterinary(6L, "Dr. Brian White", "brianwhite@vet.com", "564920283", "303 Animal St."),
            new Veterinary(7L, "Dr. Lisa Green", "lisagreen@vet.com", "193847562", "404 Vet Ave."),
            new Veterinary(8L, "Dr. James Brown", "jamesbrown@vet.com", "209384756", "505 Animal Blvd."),
            new Veterinary(9L, "Dr. Kate Blue", "kateblue@vet.com", "348576293", "606 Care Ln."),
            new Veterinary(10L, "Dr. David Black", "davidblack@vet.com", "657483920", "707 Vet Rd.")
        ));
    }

    @Override
    public List<Veterinary> findAll() {
        return veterinaries;
    }

    @Override
    public Optional<Veterinary> findById(Long id) {
        return veterinaries.stream()
                .filter(vet -> vet.getId().equals(id))
                .findFirst();
    }
}
