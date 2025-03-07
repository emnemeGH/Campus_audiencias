package com.example.prueba2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.services.Audiencia_extService;

@RestController
@RequestMapping("/api/audienciasExt")
@CrossOrigin(origins = "http://localhost:4200")
public class Audiencia_extController extends BaseController<Audiencia_ext, Integer> {

    private final Audiencia_extService Audiencia_extService;

    public Audiencia_extController(Audiencia_extService Audiencia_extService) {
        super(Audiencia_extService);
        this.Audiencia_extService = Audiencia_extService;
    }

    @PostMapping("/api/audienciasExt")
    public ResponseEntity<?> crearAudienciaExt(@RequestBody Audiencia_ext audienciaExt) {
        try {
            Audiencia_ext nuevaAudienciaExt = Audiencia_extService.guardar(audienciaExt);
            return ResponseEntity.ok(nuevaAudienciaExt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // 🔹 Devuelve el error si hay conflicto
        }
    }

    

}