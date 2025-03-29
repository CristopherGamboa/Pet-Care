package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.models.Invoice;
import com.petcare.petcare.repositories.interfaces.IInvoiceRepository;
import com.petcare.petcare.services.interfaces.IInvoiceService;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceRepository)
    {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }
}
