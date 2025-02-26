package com.example.prueba2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Sala;
import com.example.prueba2.services.SalaService;

@RestController
public class SalaController {
    @Autowired
    private SalaService salaService;

    @GetMapping("/salas")
    public List<Sala> obtenerSalas(){
        return salaService.obtenerSalas();
    }
    
}
