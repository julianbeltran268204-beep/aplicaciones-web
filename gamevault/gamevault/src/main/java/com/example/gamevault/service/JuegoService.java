package com.example.gamevault.service;

import com.example.gamevault.model.Juego;
import com.example.gamevault.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class JuegoService {
    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> listarTodo() {
        return juegoRepository.findAll();
    }

    public String guardarJuego(@RequestParam("titulo") String titulo,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("portada") MultipartFile portada) {

        String nombreArchivo = "default.png";

        if (!portada.isEmpty()) {


            // Guardamos el juego en nuestra "base de datos"
            Juego nuevoJuego = new Juego(idcounter++, titulo, descripcion, nombreArchivo);
            juegosdb.add(nuevoJuego);
        }
    }

}
