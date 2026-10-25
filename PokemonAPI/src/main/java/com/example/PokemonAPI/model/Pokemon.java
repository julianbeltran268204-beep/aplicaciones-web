package com.example.PokemonAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity mapea esta clase a la tabla "pokemones" en MySQL.
@Data // Lombok genera getters/setters, equals, hashCode y toString.
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pokemones")
public class Pokemon {

    // Llave primaria con AUTO_INCREMENT.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false: la BD rechaza pokémon sin nombre.
    @Column(nullable = false)
    private String nombre;

    @Column
    private String tipo;

    // Se usa Integer (no int) para que pueda ser null; así el PATCH detecta qué campos NO se enviaron.
    @Column
    private Integer nivel;

    @Column
    private Integer ataque;

    @Column
    private Integer defensa;

    @Column(length = 1000) // Permite descripciones largas.
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;
}
