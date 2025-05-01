package com.petcare.petcare.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.petcare.petcare.dtos.CustomerRequest;
import com.petcare.petcare.models.Customer;
import com.petcare.petcare.repositories.interfaces.ICustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @InjectMocks
    private CustomerService customerService;

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
    @DisplayName("Service save customer test")
    public void saveCustomerTest() {
        CustomerRequest request = CustomerRequest.builder()
            .name("John Doe")
            .address("123 Main St")
            .phone("555-555-5555")
            .email("johndoe@me.com")
            .build();

        Customer mappedCustomer = Customer.builder()
            .name(request.getName())
            .address(request.getAddress())
            .phone(request.getPhone())
            .email(request.getEmail())
            .build();

        
        when(customerRepository.save(mappedCustomer)).thenReturn(mappedCustomer);

        Customer savedCustomer = customerService.save(request).get();

        assertNotNull(savedCustomer);
        assertEquals(mappedCustomer.getName(), savedCustomer.getName());
        assertEquals(mappedCustomer.getAddress(), savedCustomer.getAddress());
        assertEquals(mappedCustomer.getEmail(), savedCustomer.getEmail());
        assertEquals(mappedCustomer.getPhone(), savedCustomer.getPhone());
    }

    @Test
    @DisplayName("Service update customer test")
    public void updateCustomerTest() {
        Long id = 1L;
        CustomerRequest request = CustomerRequest.builder()
            .name("John Doe")
            .address("123 Main St")
            .phone("555-555-5555")
            .email("johndoe@me.com")
            .build();

        Customer mappedCustomer = Customer.builder()
            .id(id)
            .name(request.getName())
            .address(request.getAddress())
            .phone(request.getPhone())
            .email(request.getEmail())
            .build();

        when(customerRepository.existsById(id)).thenReturn(true);
        when(customerRepository.save(mappedCustomer)).thenReturn(mappedCustomer);

        Customer savedCustomer = customerService.update(id, request).get();

        assertNotNull(savedCustomer);
        assertEquals(mappedCustomer.getName(), savedCustomer.getName());
        assertEquals(mappedCustomer.getAddress(), savedCustomer.getAddress());
        assertEquals(mappedCustomer.getEmail(), savedCustomer.getEmail());
        assertEquals(mappedCustomer.getPhone(), savedCustomer.getPhone());
    }
}
