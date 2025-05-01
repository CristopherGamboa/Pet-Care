package com.petcare.petcare.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.petcare.petcare.models.Customer;
import com.petcare.petcare.services.CustomerService;
import com.petcare.petcare.services.CustomerServiceTest;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("==== Initializing [{}] ====", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("==== Finished [{}] ====", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Controller findAll returns HATEOAS structure")
    public void findAllTest() {
        Customer customer = Customer.builder()
            .id(1L)
            .name("Pet Fest")
            .build();

        when(customerService.findAll()).thenReturn(List.of(customer));

        CollectionModel<EntityModel<Customer>> result = customerController.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.getContent().size());

        EntityModel<Customer> customerModel = result.getContent().iterator().next();
        assertEquals("Pet Fest", customerModel.getContent().getName());

        assertTrue(
            customerModel.getLinks().hasLink("self"),
            "Should contain self link"
        );

        assertTrue(
            result.getLinks().hasLink("customers"),
            "Should contain customers link"
        );
    }

    @Test
    @DisplayName("Controller findById returns entity with HATEOAS links")
    public void findByIdTest() {
        Long id = 1L;
        Customer customer = Customer.builder()
            .id(id)
            .name("Pet Fest")
            .build();

        when(customerService.findById(id)).thenReturn(Optional.of(customer));

        EntityModel<Customer> result = customerController.getCustomerById(id);

        assertNotNull(result);
        assertEquals("Pet Fest", result.getContent().getName());

        assertTrue(result.getLinks().hasLink("self"));
        assertTrue(result.getLinks().hasLink("customers"));
    }
}

