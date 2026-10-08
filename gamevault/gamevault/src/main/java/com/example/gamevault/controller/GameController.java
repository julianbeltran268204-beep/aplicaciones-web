package com.example.gamevault.controller;

import com.example.gamevault.config.WebConfig;
import com.example.gamevault.model.Juego;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class GameController {

    private static final List<Juego> juegosdb = new ArrayList<>();
    private static long idcounter = 1;

    private static final String UPLOAD_DIR="src/main/resources/static/uploads/";

    @GetMapping("/fragments-demo")
    public String fragments() {
        return "fragments-demo";
    }

    @GetMapping({"/", "/juegos"})
    public String juegos(Model model) {
        model.addAttribute("juegos", juegosdb);
        return "juegos";
    }

    @GetMapping("/juegos/nuevo")
    public String mostrarFormulario() {
        return "formulario";
    }

    @PostMapping("/juegos")
    public String guardarJuego(@RequestParam("titulo") String titulo,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("portada") MultipartFile portada) {

        String nombreArchivo = "default.png";

        if (!portada.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }


                nombreArchivo = UUID.randomUUID().toString() + "_" + portada.getOriginalFilename();
                Path filePath = uploadPath.resolve(nombreArchivo);

                Files.copy(portada.getInputStream(), filePath);

                System.out.println("Archivo guardado en: " + filePath.toAbsolutePath());


            } catch (IOException e) {
                e.printStackTrace();
                // En un proyecto real, manejaríamos esta excepción apropiadamente (ej. redirigir con error)
            }
        }


        // Guardamos el juego en nuestra "base de datos"
        Juego nuevoJuego = new Juego(idcounter++, titulo, descripcion, nombreArchivo);
        juegosdb.add(nuevoJuego);


        // Redirigimos a la lista de juegos (Patrón PRG - Post/Redirect/Get)
        return "redirect:/juegos";
    }

}

