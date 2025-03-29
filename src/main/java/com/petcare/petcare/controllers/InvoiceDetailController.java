package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.petcare.models.InvoiceDetail;
import com.petcare.petcare.services.interfaces.IInvoiceDetailService;

@RestController
@RequestMapping("api/invoice-details")
public class InvoiceDetailController {
    private final IInvoiceDetailService invoiceDetailService;

    @Autowired
    public InvoiceDetailController(IInvoiceDetailService invoiceDetailService) {
        this.invoiceDetailService = invoiceDetailService;
    }

    // Obtiene todos los detalles de facturas
    // ej: http://localhost:8080/api/invoice-details
    @GetMapping
    public List<InvoiceDetail> getAllInvoiceDetails() {
        return invoiceDetailService.findAll();
    }

    // Obtiene un detalle de factura por id
    // ej: http://localhost:8080/api/invoice-details/1
    @GetMapping("/{id}")
    public Optional<InvoiceDetail> getInvoiceDetailById(@PathVariable Long id) {
        return invoiceDetailService.findById(id);
    }

    // Obtiene todos los detalles de facturas por id de factura
    // ej: http://localhost:8080/api/invoice-details/invoice/1
    @GetMapping("/invoice/{id}")
    public List<InvoiceDetail> getInvoiceDetailsByInvoiceId(@PathVariable Long id) {
        return invoiceDetailService.findByInvoiceId(id);
    }
}
