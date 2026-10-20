package com.example.ResorucesHub.repository;

import com.example.ResorucesHub.model.Recurso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // Componente de persistencia de Spring (almacenamiento en memoria)
public class RecursoRepository {

    private final List<Recurso> recursos = new ArrayList<>();
    private Long contadorId = 1L;

    public List<Recurso> findAll() {
        return recursos;
    }

    public Optional<Recurso> findById(Long id) {
        return recursos.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public Recurso save(Recurso recurso) {
        if (recurso.getId() == null) {
            // Creación (POST)
            recurso.setId(contadorId++);
            recursos.add(recurso);
        } else {
            // Actualización (PUT): buscamos el índice y reemplazamos
            for (int i = 0; i < recursos.size(); i++) {
                if (recursos.get(i).getId().equals(recurso.getId())) {
                    recursos.set(i, recurso);
                    break;
                }
            }
        }
        return recurso;
    }

    public boolean deleteById(Long id) {
        return recursos.removeIf(r -> r.getId().equals(id));
    }
}
