package com.petcare.petcare.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.petcare.petcare.dtos.InvoiceDetailRequest;
import com.petcare.petcare.models.InvoiceDetail;

public interface IInvoiceDetailService {
    public Optional<InvoiceDetail> save(InvoiceDetailRequest request);
    public Optional<InvoiceDetail> update(Long id, InvoiceDetailRequest request);
    public void delete(Long id);
    public Optional<InvoiceDetail> findById(Long id);
    public List<InvoiceDetail> findAll();
    public List<InvoiceDetail> findByInvoiceId(Long id);
}
