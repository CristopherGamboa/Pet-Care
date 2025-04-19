package com.petcare.petcare.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.models.Invoice;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> { }
