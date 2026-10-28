package com.example.PokemonAPI.exception;

import com.example.PokemonAPI.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException ex){
        List<String> detalles = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            detalles.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ErrorDTO errorDTO = new ErrorDTO(
                "Error en la validacion de datos de entrada",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ErrorDTO> ManejarRecursosNoEncontrados(ResourcesNotFoundException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(
                "El recurso no existe en el sistema.",
                detalles,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
