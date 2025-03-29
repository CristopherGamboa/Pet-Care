package com.petcare.petcare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InvoiceDetail {
    private Long id;
    private Long invoiceId;
    private Long serviceId;
    private Long quantity;
}
