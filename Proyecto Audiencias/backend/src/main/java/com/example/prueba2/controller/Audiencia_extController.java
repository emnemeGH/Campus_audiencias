package com.example.prueba2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.services.Audiencia_extService;

@RestController
@RequestMapping("/api/audienciasExt")
@CrossOrigin(origins = "http://localhost:4200")
public class Audiencia_extController extends BaseController<Audiencia_ext, Integer> {

    private final Audiencia_extService audiencia_extService;

    public Audiencia_extController(Audiencia_extService audiencia_extService) {
        super(audiencia_extService);
        this.audiencia_extService = audiencia_extService;
    }

    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable Integer id) {
        audiencia_extService.borradoLogico(id);
    }   

}