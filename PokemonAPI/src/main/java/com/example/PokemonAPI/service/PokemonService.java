package com.example.PokemonAPI.service;

import com.example.PokemonAPI.exception.BusinessRuleException;
import com.example.PokemonAPI.exception.ResourcesNotFoundException;
import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> listar() {
        return pokemonRepository.findAll();
    }

    public Pokemon listarPorId(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("No se encontro el id de la pokemon" +id));
    }

    public Pokemon guardar(Pokemon pokemon) {
        if(pokemonRepository.existsByNombreIgnoreCase(pokemon.getNombre())){
            throw new BusinessRuleException("Ya existe un pokemon con ese nombre");
        }
        return pokemonRepository.save(pokemon);
    }
    public Pokemon actualizarPokemonCompleto(Long id, Pokemon pokemonActualizado) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setNombre(pokemonActualizado.getNombre());
            pokemon.setTipo(pokemonActualizado.getTipo());
            pokemon.setNivel(pokemonActualizado.getNivel());
            pokemon.setAtaque(pokemonActualizado.getAtaque());
            pokemon.setDefensa(pokemonActualizado.getDefensa());
            pokemon.setDescripcion(pokemonActualizado.getDescripcion());
            pokemon.setImagenUrl(pokemonActualizado.getImagenUrl());
            return pokemonRepository.save(pokemon);
        }).orElse(null);
    }

    // PATCH permite modificar solamente algunos campos del recurso.
    // En lugar de sobrescribir todo a ciegas, revisamos qué valores vienen en la petición (no son null)
    // y solo actualizamos esos.
    public Pokemon actualizarPokemonParcial(Long id, Pokemon pokemonParcial) {
        return pokemonRepository.findById(id).map(pokemon -> {
            if (pokemonParcial.getNombre() != null) pokemon.setNombre(pokemonParcial.getNombre());
            if (pokemonParcial.getTipo() != null) pokemon.setTipo(pokemonParcial.getTipo());
            if (pokemonParcial.getNivel() != null) pokemon.setNivel(pokemonParcial.getNivel());
            if (pokemonParcial.getAtaque() != null) pokemon.setAtaque(pokemonParcial.getAtaque());
            if (pokemonParcial.getDefensa() != null) pokemon.setDefensa(pokemonParcial.getDefensa());
            if (pokemonParcial.getDescripcion() != null) pokemon.setDescripcion(pokemonParcial.getDescripcion());
            if (pokemonParcial.getImagenUrl() != null) pokemon.setImagenUrl(pokemonParcial.getImagenUrl());

            return pokemonRepository.save(pokemon);
        }).orElse(null);
    }

    public void eliminarPokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

}