package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<Entidad, TipoDelId> ya incluye findAll, findById, save, deleteById, etc.
// No hay que escribir SQL ni implementar nada.
@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
