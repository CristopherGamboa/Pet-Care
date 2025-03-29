package com.petcare.petcare.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petcare.petcare.models.Customer;
import com.petcare.petcare.repositories.interfaces.ICustomerRepository;

@Repository
public class CustomerRepository implements ICustomerRepository {

    private List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>(List.of(
            new Customer(1L, "Juan Pérez", "juan@example.com", "123456789", "Calle 123, Santiago"),
            new Customer(2L, "Carlos Pérez", "carlos.perez@cliente.com", "9876543210", "Avenida 2, Barcelona"),
            new Customer(3L, "Cristopher Gamboa", "cristopher@example.com", "564738291", "Pasaje Los Olmos 789, Concepción"),
            new Customer(4L, "Javier Rodríguez", "javier.rodriguez@cliente.com", "6574839201", "Calle 4, Sevilla"),
            new Customer(5L, "Pedro Ramírez", "pedro@example.com", "998877665", "Las Palmas 567, Temuco"),
            new Customer(6L, "Sofía Herrera", "sofia@example.com", "776655443", "Cerro Alegre 876, Viña del Mar"),
            new Customer(7L, "Luis Fernández", "luis@example.com", "554433221", "Gran Avenida 432, Rancagua"),
            new Customer(8L, "Ricardo Torres", "ricardo.torres@cliente.com", "2093847561", "Calle 8, Alicante"),
            new Customer(9L, "Elena Díaz", "elena.diaz@cliente.com", "3485762930", "Plaza 9, Valencia"),
            new Customer(10L, "Juan Martínez", "juan.martinez@cliente.com", "6574839202", "Calle 10, Granada")
        ));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }
}
