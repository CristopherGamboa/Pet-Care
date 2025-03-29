package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.petcare.models.Invoice;
import com.petcare.petcare.services.interfaces.IInvoiceService;


@RestController
@RequestMapping("api/invoices")
public class InvoiceController {
    private final IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Obtiene todas las facturas
    // ej: http://localhost:8080/api/invoices
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.findAll();
    }

    // Obtiene una factura por id
    // ej: http://localhost:8080/api/invoices/1
    @GetMapping("/{id}")
    public Optional<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.findById(id);
    }
}
