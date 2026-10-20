package com.example.ResorucesHub.controller;

import com.example.ResorucesHub.model.Recurso;
import com.example.ResorucesHub.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
//@RequestMapping("/api/videojuegos")
//@RequestMapping("/api/usuarios")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping
    public List<Recurso> listar() {
        return recursoService.obtenerTodos();
    }

    @PostMapping
    public Recurso crear(@RequestBody Recurso recurso) {
        return  recursoService.crearRecurso(recurso);
    }

    @PutMapping({"/{id}"})
    public Recurso actualizar(@PathVariable Long id, @RequestBody Recurso recurso) {
        return recursoService.actualizar(id, recurso).orElse(null);
    }

    @PatchMapping({"/{id}"})
    public Recurso actualizar(@PathVariable Long id, @RequestBody Recurso recurso) {
        return recursoService.actualizarParcial(id, recurso).orElse(null);
    }

    @DeleteMapping({"/{id}"})
    public void eliminar(@PathVariable Long id) {
        recursoService.eliminarRecurso(id);
    }
}