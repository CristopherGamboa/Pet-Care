package com.petcare.petcare.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.petcare.petcare.models.Customer;
import com.petcare.petcare.repositories.interfaces.ICustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryTest.class);
    
    @Mock
    private ICustomerRepository customerRepository;

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
            .id(1L)
            .name("John Doe")
            .address("123 Main St")
            .phone("555-555-5555")
            .email("johndoe@me.com")
            .build();

        when(customerRepository.save(customer)).thenReturn(customer);
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
            .id(1L)
            .name("John Doe")
            .address("123 Main St")
            .phone("555-555-5555")
            .email("johndoe@me.com")
            .build();

        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerRepository.save(customer);

        when(customerRepository.findById(savedCustomer.getId())).thenReturn(Optional.of(savedCustomer));
        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());

        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getId(), foundCustomer.get().getId());
        assertEquals(savedCustomer.getName(), foundCustomer.get().getName());
    }
}
