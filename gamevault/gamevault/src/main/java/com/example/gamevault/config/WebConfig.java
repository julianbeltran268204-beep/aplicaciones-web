package com.example.gamevault.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Carpeta donde se guardan las portadas (se crea en la raíz del proyecto)
    public static final Path UPLOAD_DIR = Paths.get("uploads").toAbsolutePath();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // toUri() genera bien la ruta "file:///C:/..." en Windows
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(UPLOAD_DIR.toUri().toString());
    }
}
