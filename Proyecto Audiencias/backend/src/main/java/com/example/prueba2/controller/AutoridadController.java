package com.example.prueba2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.services.AutoridadService;

@RestController

public class AutoridadController {
    @Autowired
    private AutoridadService autoridadService;
    
    @GetMapping("/autoridades")
    public List<Autoridad> obtenerAutoridades(){
        return autoridadService.obtenerAutoridades();
    }
}
