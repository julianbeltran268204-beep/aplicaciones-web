package com.example.gamevault.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import java.util.Map;
import java.util.UUID;

@Service
public class JuegoService {
    @Autowired
    private JuegoRepository juegoRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private Cloudinary cloudinary;

    public List<Juego> listarTodo() {
        return juegoRepository.findAll();
    }

    public void guardarJuego(Juego juego, MultipartFile portada) {
        String nombreArchivo = "default.png";

        if (!portada.isEmpty()) {
            nombreArchivo = guardarImagenCloudinary(portada);
        }

        juego.setPortadaUrl(nombreArchivo);
        juego.setGenero("");
        juego.setActivo(true);
        juegoRepository.save(juego);
    }

    public String guardarImagenEnProyecto(MultipartFile portada){
        try {
            // Tip para clase: Crear la carpeta si no existe
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generamos un nombre único para evitar sobreescribir archivos con el mismo nombre
            String nombreArchivo = UUID.randomUUID().toString() + "_" + portada.getOriginalFilename();
            Path filePath = uploadPath.resolve(nombreArchivo);


            // Guardamos el archivo físicamente en disco
            Files.copy(portada.getInputStream(), filePath);


            System.out.println("Archivo guardado en: " + filePath.toAbsolutePath());


            return nombreArchivo;
        } catch (IOException e) {
            e.printStackTrace();
            return "default.png";
            // En un proyecto real, manejaríamos esta excepción apropiadamente (ej. redirigir con error
        }
    }
    private String guardarImagenCloudinary(MultipartFile portada) {
        try {
            // Sube el archivo a Cloudinary
            Map uploadResult = cloudinary.uploader().upload(portada.getBytes(), ObjectUtils.emptyMap());
            // Obtiene la URL pública generada por Cloudinary
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            System.err.println("Error al subir a Cloudinary. Revisa tus credenciales.");
            e.printStackTrace();
            return "default.png";
        }
    }


}