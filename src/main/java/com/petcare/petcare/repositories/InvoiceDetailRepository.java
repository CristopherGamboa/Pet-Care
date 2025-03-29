package com.petcare.petcare.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.petcare.petcare.models.InvoiceDetail;
import com.petcare.petcare.repositories.interfaces.IInvoiceDetailRepository;

@Repository
public class InvoiceDetailRepository implements IInvoiceDetailRepository {

    private List<InvoiceDetail> invoiceDetails;

    public InvoiceDetailRepository() {
        this.invoiceDetails = new ArrayList<>(List.of(
            new InvoiceDetail(1L, 1L, 1L, 1L),  
            new InvoiceDetail(2L, 2L, 2L, 1L),  
            new InvoiceDetail(3L, 3L, 3L, 1L),  
            new InvoiceDetail(4L, 4L, 4L, 1L),  
            new InvoiceDetail(5L, 5L, 5L, 1L),  
            new InvoiceDetail(6L, 6L, 6L, 1L),  
            new InvoiceDetail(7L, 7L, 7L, 1L),  
            new InvoiceDetail(8L, 8L, 8L, 1L),  
            new InvoiceDetail(9L, 9L, 9L, 1L),  
            new InvoiceDetail(10L, 10L, 10L, 1L)  
        ));
    }

    @Override
    public List<InvoiceDetail> findAll() {
        return invoiceDetails;
    }

    @Override
    public Optional<InvoiceDetail> findById(Long id) {
        return invoiceDetails.stream()
                .filter(invoiceDetail -> invoiceDetail.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<InvoiceDetail> findByInvoiceId(Long id) {
        return invoiceDetails.stream()
                .filter(invoiceDetail -> invoiceDetail.getInvoiceId().equals(id))
                .collect(Collectors.toList());
    }
    
}
