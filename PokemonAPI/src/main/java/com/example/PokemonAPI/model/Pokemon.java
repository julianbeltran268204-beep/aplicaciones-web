package com.example.PokemonAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

// @Entity mapea esta clase a la tabla "pokemones" en MySQL.
@Data // Lombok genera getters/setters, equals, hashCode y toString.
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pokemones")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "No vacio")
    @Size(min = 2, max=50, message= "El nombre debe tener entre 2 a 50 caracteres")
    private String nombre;

    @NotBlank(message = "No vacio")
    private String tipo;

    @NotNull(message = "El nivel es requerido")
    @Min(value = 1, message = "el minimo es 1")
    @Min(value = 1, message = "el minimo es 100")
    private Integer nivel;

    @Min(value = 1, message = "el minimo es 1")
    private Integer ataque;

    @Min(value = 1, message = "el minimo es 1")
    private Integer defensa;

    @Size(max = 255, message = "no puede ser mayor a 255 la desc")
    private String descripcion;

    @URL(message = "La imagen debe ser una url")
    private String imagenUrl;

    @Email(message = "el email debe tener formato valido")
    private String correoEntrenador;

    @PastOrPresent(message = "la fecha de nacimiento no puede ser mayor a la actual")
    private LocalDate fechaNacimiento;

    @Positive(message = "el peso debe ser mayor a 0")
    private Double peso;

    @Pattern(regexp = "^(\\\\d{3}[- ]?){2}\\\\d{4}$", message = "el formato del telefono no es valido")
    private String telefonoEntrenador;

    @AssertTrue(message = "el pokemon debe estar sano")
    private Boolean estaSano;

}
