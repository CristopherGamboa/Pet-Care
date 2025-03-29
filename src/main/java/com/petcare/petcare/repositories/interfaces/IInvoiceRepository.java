package com.petcare.petcare.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.Invoice;

public interface IInvoiceRepository {
    public Optional<Invoice> findById(Long id);
    public List<Invoice> findAll();
}
