package com.example.prueba2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.services.AudienciaService;

@RestController

public class AudienciaController {
    @Autowired
    private AudienciaService audienciaService;
    
    @GetMapping("/audiencias")
    public List<Audiencia> obtenerAudiencias(){
        return audienciaService.obtenerAudiencias();
    }
}
