package com.example.PokemonAPI.dto;

import com.example.PokemonAPI.model.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private List<String> detalles;
    private LocalDateTime fecha;

}
