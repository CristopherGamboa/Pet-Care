package com.petcare.petcare.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.petcare.petcare.models.Service;
import com.petcare.petcare.repositories.interfaces.IServiceRepository;

@Repository
public class ServiceRepository implements IServiceRepository {
    private final List<Service> services;

    public ServiceRepository() {
        this.services = new ArrayList<>(List.of(
            new Service(1L, "Consulta General", "Consulta veterinaria general para el diagnóstico de enfermedades comunes", 20000L),
            new Service(2L, "Vacunación", "Vacunación de perros y gatos contra enfermedades comunes", 15000L),
            new Service(3L, "Desparacitación", "Tratamiento para eliminar parásitos internos y externos en mascotas", 10000L),
            new Service(4L, "Examen de Sangre", "Examen de sangre completo para diagnóstico de enfermedades", 30000L),
            new Service(5L, "Esterilización", "Esterilización quirúrgica de perros y gatos", 40000L),
            new Service(6L, "Radiografía", "Radiografía para diagnóstico de lesiones internas", 25000L),
            new Service(7L, "Consulta de Urgencias", "Atención veterinaria en caso de urgencias", 35000L),
            new Service(8L, "Revisión Postoperatoria", "Revisión después de cirugía para asegurar una recuperación adecuada", 18000L),
            new Service(9L, "Limpieza Dental", "Limpieza profesional de dientes para prevenir enfermedades dentales", 22000L),
            new Service(10L, "Baño y Aseo", "Baño y aseo completo para perros y gatos", 12000L)
        ));
    }

    @Override
    public List<Service> findAll() {
        return services;
    }

    @Override
    public Optional<Service> findById(Long id) {
        return services.stream().filter(service -> service.getId().equals(id)).findFirst();
    }
}
