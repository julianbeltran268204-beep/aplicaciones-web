package com.example.miPrimerServidor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return"Hola desde servidor aplicacion";
    }
    @GetMapping("/saludo")
    public String saludo(@RequestParam String nombre){
        return"Hola "+ nombre +" desde servidor apps";
    }
    @PostMapping("/mensaje")
    public String recibirMensaje(@RequestBody String mensaje){
        return "recibi el mensaje "+mensaje;
    }
}
