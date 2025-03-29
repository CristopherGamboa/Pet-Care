package com.petcare.petcare.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.models.InvoiceDetail;

public interface IInvoiceDetailRepository {
    public Optional<InvoiceDetail> findById(Long id);
    public List<InvoiceDetail> findAll();
    public List<InvoiceDetail> findByInvoiceId(Long id);
}
