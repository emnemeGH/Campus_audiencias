package com.example.prueba2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.services.AutoridadService;

@RestController
@RequestMapping("/api/autoridades")
@CrossOrigin(origins = "http://localhost:4200")
public class AutoridadController extends BaseController<Autoridad, Integer> {

    private AutoridadService autoridadService;

    public AutoridadController(AutoridadService autoridadService) {
        super(autoridadService);
        this.autoridadService = autoridadService;
    }

    @PostMapping("/registrarAut")
    public ResponseEntity<?> registrarAutoridad(@RequestBody Autoridad autoridad) {
    try {
        Autoridad nuevaAutoridad = autoridadService.guardarAutoridad(autoridad);
        return ResponseEntity.ok(nuevaAutoridad);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());  // ⚠️ Respuesta 400 con el mensaje del error
    }
}

    @DeleteMapping(("/{id}"))
    public void eliminarPorId(@PathVariable Integer id) {
        autoridadService.borradoLogico(id);
    }
}
