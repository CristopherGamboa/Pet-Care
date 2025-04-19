package com.petcare.petcare.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.models.InvoiceDetail;

public interface IInvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> { 
    List<InvoiceDetail> findByInvoiceId(Long id);
}
