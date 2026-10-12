package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Juego {
    private Long id;
    private String titulo;
    private String descripción;
    private String portadaUrl;
}