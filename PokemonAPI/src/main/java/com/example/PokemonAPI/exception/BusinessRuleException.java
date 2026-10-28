package com.example.PokemonAPI.exception;

import com.example.PokemonAPI.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ErrorDTO> manejarReglasDeNegocio(ResourcesNotFoundException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getMessage());

        ErrorDTO errorDTO = new ErrorDTO(
                "El recurso no existe en el sistema.",
                detalles,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ErrorDTO> errorDelProgramador(ResourcesNotFoundException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(
                "Ocurrio un error interno en el sistema.",
                detalles,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
