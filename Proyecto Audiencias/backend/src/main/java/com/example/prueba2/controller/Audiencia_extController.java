package com.example.prueba2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.services.Audiencia_extService;

@RestController
public class Audiencia_extController {
    @Autowired
    private Audiencia_extService audiencia_extService;

    @GetMapping("/audienciasExt")
    public List<Audiencia_ext> obtenerAudienciaExt(){
        return audiencia_extService.obtenerAudienciaExt();
    }
}