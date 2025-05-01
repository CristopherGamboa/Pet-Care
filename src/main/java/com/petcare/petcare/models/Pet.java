package com.petcare.petcare.models;

import org.springframework.hateoas.RepresentationModel;

import com.petcare.petcare.enums.PetType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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
@Entity
@Table(name = "pet")
public class Pet extends RepresentationModel<Pet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Getter(AccessLevel.NONE)
    private PetType type;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private PetOwner owner;
    
    public String getPetType() {
        return type.getDisplayName();
    }
}
