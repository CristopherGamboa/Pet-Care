package com.petcare.petcare.dtos;

import java.util.Date;

import com.petcare.petcare.enums.InvoiceStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceRequest {
    @PastOrPresent(message = "Creation date must be in the past or present")
    @NotNull(message = "Creation date is required")
    private Date creationDate; 

    @NotNull(message = "Total is required")
    @Min(value = 0, message = "Total must be greater than or equal to 0")
    private Long total;

    @NotNull(message = "Status is required")
    private InvoiceStatus status;

    @NotNull(message = "Pet ID is required")
    private Long petId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;
}
