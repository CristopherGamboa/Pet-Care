package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.dtos.InvoiceRequest;
import com.petcare.petcare.models.Customer;
import com.petcare.petcare.models.Invoice;
import com.petcare.petcare.models.Pet;
import com.petcare.petcare.repositories.interfaces.ICustomerRepository;
import com.petcare.petcare.repositories.interfaces.IInvoiceRepository;
import com.petcare.petcare.services.interfaces.IInvoiceService;
import com.petcare.petcare.services.interfaces.IPetService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceRepository invoiceRepository;
    private final ICustomerRepository customerRepository;
    private final IPetService petService;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceRepository,
        ICustomerRepository customerRepository,
        IPetService petService)
    {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.petService = petService;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Optional<Invoice> save(InvoiceRequest request) {
        Pet pet = petService.findById(request.getPetId())
            .orElseThrow(() -> new EntityNotFoundException("Pet with id " + request.getPetId() + " not found."));

        Customer customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new EntityNotFoundException("Customer with id " + request.getCustomerId() + " not found."));
        
        Invoice invoice = Invoice.builder()
            .creationDate(request.getCreationDate())
            .total(request.getTotal())
            .status(request.getStatus())
            .pet(pet)
            .customer(customer)
            .build();
        
        return Optional.of(invoiceRepository.save(invoice));
    }

    @Override
    public Optional<Invoice> update(Long id, InvoiceRequest request) {
        if (!invoiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Invoice with id " + id + " not found.");
        }

        Pet pet = petService.findById(request.getPetId())
            .orElseThrow(() -> new EntityNotFoundException("Pet with id " + request.getPetId() + " not found."));

        Customer customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new EntityNotFoundException("Customer with id " + request.getCustomerId() + " not found."));
        
        Invoice invoice = Invoice.builder()
            .id(id)
            .creationDate(request.getCreationDate())
            .total(request.getTotal())
            .status(request.getStatus())
            .pet(pet)
            .customer(customer)
            .build();

        return Optional.of(invoiceRepository.save(invoice));
    }

    @Override
    public void delete(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Invoice with id " + id + " not found.");
        }
        
        invoiceRepository.deleteById(id);
    }
}
