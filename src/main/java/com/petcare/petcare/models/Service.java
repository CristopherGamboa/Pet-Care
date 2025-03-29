package com.petcare.petcare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Service {
    private Long id;
    private String name;
    private String description;
    private Long price;
}
