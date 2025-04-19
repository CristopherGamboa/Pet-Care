package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.dtos.InvoiceDetailRequest;
import com.petcare.petcare.models.Invoice;
import com.petcare.petcare.models.InvoiceDetail;
import com.petcare.petcare.repositories.interfaces.IInvoiceDetailRepository;
import com.petcare.petcare.repositories.interfaces.IInvoiceRepository;
import com.petcare.petcare.repositories.interfaces.IServiceRepository;
import com.petcare.petcare.services.interfaces.IInvoiceDetailService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvoiceDetailService implements IInvoiceDetailService {

    private final IInvoiceDetailRepository invoiceDetailRepository;
    private final IInvoiceRepository invoiceRepository;
    private final IServiceRepository serviceRepository;
    
    @Autowired
    public InvoiceDetailService(
        IInvoiceDetailRepository invoiceDetailRepository,
        IInvoiceRepository invoiceRepository,
        IServiceRepository serviceRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
        this.invoiceRepository = invoiceRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<InvoiceDetail> findAll() {
        return invoiceDetailRepository.findAll();
    }

    @Override
    public Optional<InvoiceDetail> findById(Long id) {
        return invoiceDetailRepository.findById(id);
    }

    @Override
    public List<InvoiceDetail> findByInvoiceId(Long id) {
        return invoiceDetailRepository.findByInvoiceId(id);
    }

    @Override
    public Optional<InvoiceDetail> save(InvoiceDetailRequest request) {
        Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
            .orElseThrow(() -> new EntityNotFoundException("Invoice with id " + request.getInvoiceId() + " not found."));

        com.petcare.petcare.models.Service service = serviceRepository.findById(request.getServiceId())
            .orElseThrow(() -> new EntityNotFoundException("Service with id " + request.getServiceId() + " not found."));

        InvoiceDetail invoiceDetail = InvoiceDetail.builder()
            .invoice(invoice)
            .service(service)
            .quantity(request.getQuantity())
            .build();

        return Optional.of(invoiceDetailRepository.save(invoiceDetail));
    }

    @Override
    public Optional<InvoiceDetail> update(Long id, InvoiceDetailRequest request) {
        if (!invoiceDetailRepository.existsById(id)) {
            return Optional.empty();
        }

        Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
            .orElseThrow(() -> new EntityNotFoundException("Invoice with id " + request.getInvoiceId() + " not found."));

        com.petcare.petcare.models.Service service = serviceRepository.findById(request.getServiceId())
            .orElseThrow(() -> new EntityNotFoundException("Service with id " + request.getServiceId() + " not found."));

        InvoiceDetail invoiceDetail = InvoiceDetail.builder()
            .id(id)
            .invoice(invoice)
            .service(service)
            .quantity(request.getQuantity())
            .build();

        return Optional.of(invoiceDetailRepository.save(invoiceDetail));
    }

    @Override
    public void delete(Long id) {
        invoiceDetailRepository.deleteById(id);
    }
}
