package com.example.gamevault.controller;

import com.example.gamevault.config.WebConfig;
import com.example.gamevault.model.Juego;
import com.example.gamevault.service.JuegoService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private JuegoService juegoService;

    private static final List<Juego> juegosdb = new ArrayList<>();
    private static long idcounter = 1;

    private static final String UPLOAD_DIR="src/main/resources/static/uploads/";

    @GetMapping("/fragments-demo")
    public String fragments() {
        return "fragments-demo";
    }

    @GetMapping({"/", "/juegos"})
    public String juegos(Model model) {
        model.addAttribute("juegos", this.juegoService.listarTodo());
        return "juegos";
    }

    @GetMapping("/juegos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("juego", new Juego());
        return "formulario";
    }

    @PostMapping("/juegos")
    public String guardarJuego(@RequestParam("titulo") String titulo,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("precio") Double precio,
                               @RequestParam("categoria") Double categoria,
                               @RequestParam("portada") MultipartFile portada) {

        Juego juego = new Juego();
        juego.setTitulo(titulo);
        juego.setDescripcion(descripcion);
        juego.setCategoria(categoria.toString());
        juego.setPrecio(precio);

        this.juegoService.guardarJuego(juego, portada);

        return "redirect:/juegos";
    }

        @PostMapping("/juegos/eliminar/{id}")
        public String eliminarJuego (@PathVariable Long id){
            this.juegoService.eliminarJuego(id);

            return "redirect:/juegos";
        }

        @GetMapping("/juegos/editar/{id}")
        public String editarJuego (@PathVariable Long id, Model model) {
            Juego juego = this.juegoService.BusccarPorId(id);

            model.addAttribute("juego", juego);
            return "formulario";
        }

    @PostMapping("/juegos/editar/{id}")
    public String actualizarJuego(@PathVariable Long id,
                               @RequestParam("titulo") String titulo,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("precio") Double precio,
                               @RequestParam("categoria") Double categoria,
                               @RequestParam(value = "portada", required = false) MultipartFile portada) {

        Juego juego = this.juegoService.BusccarPorId(id);
        juego.setTitulo(titulo);
        juego.setDescripcion(descripcion);
        juego.setCategoria(categoria.toString());
        juego.setPrecio(precio);

        this.juegoService.guardarJuego(juego, portada);

        return "redirect:/juegos";
    }


}

