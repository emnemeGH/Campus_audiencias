package com.example.prueba2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.services.AudienciaService;

@RestController
@RequestMapping("/api/audiencias")
@CrossOrigin(origins = "http://localhost:4200")
public class AudienciaController extends BaseController<Audiencia, Integer> {

    public AudienciaController(AudienciaService service) {
        super(service);
    }
}


