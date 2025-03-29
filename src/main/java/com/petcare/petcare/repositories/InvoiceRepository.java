package com.petcare.petcare.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petcare.petcare.enums.InvoiceStatus;
import com.petcare.petcare.models.Invoice;
import com.petcare.petcare.repositories.interfaces.IInvoiceRepository;

@Repository
public class InvoiceRepository implements IInvoiceRepository {

    private List<Invoice> invoices;

    public InvoiceRepository() {
        this.invoices = new ArrayList<>(List.of(
            new Invoice(1L, new Date(), 20000L, InvoiceStatus.PAID, 1L, 1L),
            new Invoice(2L, new Date(), 15000L, InvoiceStatus.PAID, 2L, 2L),
            new Invoice(3L, new Date(), 10000L, InvoiceStatus.PENDING, 3L, 3L),
            new Invoice(4L, new Date(), 30000L, InvoiceStatus.PAID, 4L, 3L),
            new Invoice(5L, new Date(), 40000L, InvoiceStatus.PENDING, 5L, 5L),
            new Invoice(6L, new Date(), 25000L, InvoiceStatus.PAID, 6L, 6L),
            new Invoice(7L, new Date(), 35000L, InvoiceStatus.PAID, 7L, 7L),
            new Invoice(8L, new Date(), 18000L, InvoiceStatus.CANCELLED, 8L, 8L),
            new Invoice(9L, new Date(), 22000L, InvoiceStatus.PAID, 9L, 9L),
            new Invoice(10L, new Date(), 12000L, InvoiceStatus.PENDING, 10L, 10L)
        ));
    }

    @Override
    public List<Invoice> findAll() {
        return invoices;
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoices.stream()
                .filter(invoice -> invoice.getId().equals(id))
                .findFirst();
    }

}
