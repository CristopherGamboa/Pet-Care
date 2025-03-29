package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Invoice;

public interface IInvoiceService {
    public Optional<Invoice> findById(Long id);
    public List<Invoice> findAll();
}
