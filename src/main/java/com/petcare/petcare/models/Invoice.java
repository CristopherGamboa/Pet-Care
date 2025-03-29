package com.petcare.petcare.models;

import java.util.Date;

import com.petcare.petcare.enums.InvoiceStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Invoice {
    private Long id;
    private Date date; 
    private Long total;
    @Getter(AccessLevel.NONE)
    private InvoiceStatus status;
    private Long petId;
    private Long customerId;

    public String getStatus() {
        return status.getDisplayName();
    }
}
