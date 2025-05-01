package com.petcare.petcare.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.petcare.petcare.models.Customer;
import com.petcare.petcare.repositories.interfaces.ICustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
        private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryTest.class);
    private final ICustomerRepository customerRepository;
    
    @Autowired
    public CustomerRepositoryTest(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("==== Initializing [{}] ====", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("==== Finished [{}] ====", testInfo.getDisplayName());
    }
    
    @Test
    @DisplayName("Repository save customer test")
    public void saveCustomerTest() {
        Customer customer = Customer.builder()
            .name("John Doe")
            .address("123 Main St")
            .phone("555-555-5555")
            .email("johndoe@me.com")
            .build();

        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer.getId());
        assertEquals(customer.getName(), savedCustomer.getName());
        assertEquals(customer.getAddress(), savedCustomer.getAddress());
        assertEquals(customer.getPhone(), savedCustomer.getPhone());
        assertEquals(customer.getEmail(), savedCustomer.getEmail());
    }

    @Test
    @DisplayName("Repository find customer by ID test")
    public void findCustomerByIdTest() {
        Customer customer = Customer.builder()
            .name("John Doe")
            .address("123 Main St")
            .phone("555-555-5555")
            .email("johndoe@me.com")
            .build();

        Customer savedCustomer = customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());

        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getId(), foundCustomer.get().getId());
        assertEquals(savedCustomer.getName(), foundCustomer.get().getName());
    }
}
