package com.example.prueba2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Distrito_judicial;
import com.example.prueba2.services.Distrito_judicialService;

@RestController
public class Distrito_judicialController {
    @Autowired
    private Distrito_judicialService distritoService;

    @GetMapping("/distritos")
    public List<Distrito_judicial> obtenerDistritos() {
        return distritoService.obtenerDistritos();
    }
}
