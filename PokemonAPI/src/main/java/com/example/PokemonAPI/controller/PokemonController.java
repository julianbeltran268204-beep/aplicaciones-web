package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: cada método responde JSON (no vistas HTML). Ruta base de la API: /api/pokemones
@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    // GET /api/pokemones -> 200 con la lista completa.
    @GetMapping
    public List<Pokemon> obtenerTodos() {
        return pokemonService.obtenerTodos();
    }

    // GET /api/pokemones/{id} -> 200 con el pokémon, o 404 si no existe.
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPorId(@PathVariable Long id) {
        return pokemonService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/pokemones -> 201 Created con el pokémon guardado (ya con su id).
    @PostMapping
    public ResponseEntity<Pokemon> crear(@RequestBody Pokemon pokemon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.crear(pokemon));
    }

    // PUT /api/pokemones/{id} -> reemplaza todos los atributos; 404 si no existe.
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarCompleto(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return pokemonService.actualizarCompleto(id, pokemon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/pokemones/{id} -> cambia solo los atributos enviados; 404 si no existe.
    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarParcial(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return pokemonService.actualizarParcial(id, pokemon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/pokemones/{id} -> 204 No Content si se borró, 404 si no existe.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return pokemonService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
