package com.petcare.petcare.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class PetOwnerRequest {
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Size(min = 3, max = 50, message = "Email must be between 3 and 50 characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone is required")
    @Size(min = 3, max = 20, message = "Phone must be between 3 and 20 characters")
    private String phone;

    @NotNull(message = "Address is required")
    @Size(min = 3, max = 100, message = "Address must be between 3 and 100 characters")
    private String address;
}
