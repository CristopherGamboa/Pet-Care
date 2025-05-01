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

import com.petcare.petcare.dtos.InvoiceDetailRequest;
import com.petcare.petcare.models.InvoiceDetail;
import com.petcare.petcare.services.interfaces.IInvoiceDetailService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

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
    public CollectionModel<EntityModel<InvoiceDetail>> getAllInvoiceDetails() {
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAll(); 

        List<EntityModel<InvoiceDetail>> invoiceDetailModels = invoiceDetails.stream()
            .map(invoiceDetail -> EntityModel.of(invoiceDetail,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getInvoiceDetailById(invoiceDetail.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoiceDetails());
        CollectionModel<EntityModel<InvoiceDetail>> invoiceDetailCollectionModel = CollectionModel
            .of(invoiceDetailModels, linkBuilder.withRel("invoiceDetails"));

        return invoiceDetailCollectionModel;
    }

    // Obtiene un detalle de factura por id
    // ej: http://localhost:8080/api/invoice-details/1
    @GetMapping("/{id}")
    public EntityModel<InvoiceDetail> getInvoiceDetailById(@PathVariable Long id) {
        Optional<InvoiceDetail> invoiceDetail = invoiceDetailService.findById(id);

        if (!invoiceDetail.isPresent()) {
            throw new EntityNotFoundException("InvoiceDetail not found with id: " + id);
        }

        return EntityModel.of(invoiceDetail.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getInvoiceDetailById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoiceDetails()).withRel("invoiceDetails"));
    }

    // Obtiene todos los detalles de facturas por id de factura
    // ej: http://localhost:8080/api/invoice-details/invoice/1
    @GetMapping("/invoice/{id}")
    public CollectionModel<EntityModel<InvoiceDetail>> getInvoiceDetailsByInvoiceId(@PathVariable Long id) {
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAll(); 

        List<EntityModel<InvoiceDetail>> invoiceDetailModels = invoiceDetails.stream()
            .map(invoiceDetail -> EntityModel.of(invoiceDetail,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getInvoiceDetailById(invoiceDetail.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoiceDetails());
        CollectionModel<EntityModel<InvoiceDetail>> invoiceDetailCollectionModel = CollectionModel
            .of(invoiceDetailModels, linkBuilder.withRel("invoiceDetails"));

        return invoiceDetailCollectionModel;
    }

    @PostMapping
    public EntityModel<InvoiceDetail> saveInvoiceDetail(@Valid @RequestBody InvoiceDetailRequest request) {
        Optional<InvoiceDetail> savedInvoiceDetail = invoiceDetailService.save(request);

        return EntityModel.of(savedInvoiceDetail.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getInvoiceDetailById(savedInvoiceDetail.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoiceDetails()).withRel("invoiceDetails"));
    }

    @PutMapping("/{id}")
    public EntityModel<InvoiceDetail> updateInvoiceDetail(@PathVariable Long id, @Valid @RequestBody InvoiceDetailRequest request) {
        Optional<InvoiceDetail> savedInvoiceDetail = invoiceDetailService.update(id, request);

        return EntityModel.of(savedInvoiceDetail.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getInvoiceDetailById(savedInvoiceDetail.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllInvoiceDetails()).withRel("invoiceDetails"));
    }

    @DeleteMapping("/{id}")
    public void deleteInvoiceDetail(@PathVariable Long id) {
        invoiceDetailService.delete(id);
    }
}
