package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.petcare.dtos.InvoiceRequest;
import com.petcare.petcare.models.Invoice;
import com.petcare.petcare.services.interfaces.IInvoiceService;

import jakarta.validation.Valid;


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

    @PostMapping
    public Optional<Invoice> saveInvoice(@Valid @RequestBody InvoiceRequest request) {
        return invoiceService.save(request);
    }

    @PutMapping("/{id}")
    public Optional<Invoice> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceRequest request) {
        return invoiceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.delete(id);
    }
}
