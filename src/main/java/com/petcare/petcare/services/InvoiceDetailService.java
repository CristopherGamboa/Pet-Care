package com.petcare.petcare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcare.petcare.models.InvoiceDetail;
import com.petcare.petcare.repositories.interfaces.IInvoiceDetailRepository;
import com.petcare.petcare.services.interfaces.IInvoiceDetailService;

@Service
public class InvoiceDetailService implements IInvoiceDetailService {

    private final IInvoiceDetailRepository invoiceDetailRepository;
    
    @Autowired
    public InvoiceDetailService(IInvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
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

}
