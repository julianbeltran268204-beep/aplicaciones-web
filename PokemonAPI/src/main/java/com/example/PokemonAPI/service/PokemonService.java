package com.example.PokemonAPI.service;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Lógica de negocio de los pokémon: consultar, crear, actualizar y eliminar.
@Service
public class PokemonService {

    // Spring inyecta el repositorio; no hagas new PokemonRepository().
    @Autowired
    private PokemonRepository pokemonRepository;

    // Obtener todos.
    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    // Obtener por ID (Optional vacío si no existe).
    public Optional<Pokemon> obtenerPorId(Long id) {
        return pokemonRepository.findById(id);
    }

    // Crear. Se fuerza id = null para que la BD siempre genere uno nuevo (el cliente no decide el ID).
    public Pokemon crear(Pokemon pokemon) {
        pokemon.setId(null);
        return pokemonRepository.save(pokemon);
    }

    // PUT: reemplaza TODOS los atributos por los recibidos.
    public Optional<Pokemon> actualizarCompleto(Long id, Pokemon nuevo) {
        return pokemonRepository.findById(id).map(existente -> {
            existente.setNombre(nuevo.getNombre());
            existente.setTipo(nuevo.getTipo());
            existente.setNivel(nuevo.getNivel());
            existente.setAtaque(nuevo.getAtaque());
            existente.setDefensa(nuevo.getDefensa());
            existente.setDescripcion(nuevo.getDescripcion());
            existente.setImagenUrl(nuevo.getImagenUrl());
            return pokemonRepository.save(existente);
        });
    }

    // PATCH: actualiza SOLO los atributos que vengan en el JSON (los null se ignoran).
    public Optional<Pokemon> actualizarParcial(Long id, Pokemon cambios) {
        return pokemonRepository.findById(id).map(existente -> {
            if (cambios.getNombre() != null) existente.setNombre(cambios.getNombre());
            if (cambios.getTipo() != null) existente.setTipo(cambios.getTipo());
            if (cambios.getNivel() != null) existente.setNivel(cambios.getNivel());
            if (cambios.getAtaque() != null) existente.setAtaque(cambios.getAtaque());
            if (cambios.getDefensa() != null) existente.setDefensa(cambios.getDefensa());
            if (cambios.getDescripcion() != null) existente.setDescripcion(cambios.getDescripcion());
            if (cambios.getImagenUrl() != null) existente.setImagenUrl(cambios.getImagenUrl());
            return pokemonRepository.save(existente);
        });
    }

    // Eliminar: regresa true si existía y se borró, false si no se encontró.
    public boolean eliminar(Long id) {
        if (!pokemonRepository.existsById(id)) {
            return false;
        }
        pokemonRepository.deleteById(id);
        return true;
    }
}
