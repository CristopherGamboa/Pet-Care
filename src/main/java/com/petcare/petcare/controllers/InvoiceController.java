package com.petcare.petcare.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import jakarta.persistence.EntityNotFoundException;
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
    public CollectionModel<EntityModel<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.findAll(); 

        List<EntityModel<Invoice>> invoiceModels = invoices.stream()
            .map(invoice -> EntityModel.of(invoice,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getInvoiceById(invoice.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoices());
        CollectionModel<EntityModel<Invoice>> invoiceCollectionModel = CollectionModel
            .of(invoiceModels, linkBuilder.withRel("invoices"));

        return invoiceCollectionModel;
    }

    // Obtiene una factura por id
    // ej: http://localhost:8080/api/invoices/1
    @GetMapping("/{id}")
    public EntityModel<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.findById(id);

        if (!invoice.isPresent()) {
            throw new EntityNotFoundException("Invoice not found with id: " + id);
        }

        return EntityModel.of(invoice.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getInvoiceById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoices()).withRel("invoices"));
    }

    @PostMapping
    public EntityModel<Invoice> saveInvoice(@Valid @RequestBody InvoiceRequest request) {
        Optional<Invoice> savedInvoice = invoiceService.save(request);

        return EntityModel.of(savedInvoice.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getInvoiceById(savedInvoice.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoices()).withRel("invoices"));
    }

    @PutMapping("/{id}")
    public EntityModel<Invoice> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceRequest request) {
        Optional<Invoice> savedInvoice = invoiceService.update(id, request);

        return EntityModel.of(savedInvoice.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getInvoiceById(savedInvoice.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoices()).withRel("invoices"));
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.delete(id);
    }
}
