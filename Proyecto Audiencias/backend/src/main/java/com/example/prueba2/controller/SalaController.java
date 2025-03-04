package com.example.prueba2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Sala;
import com.example.prueba2.services.SalaService;

@RestController
@RequestMapping("/api/salas")
@CrossOrigin(origins = "http://localhost:4200")
public class SalaController extends BaseController<Sala, Integer> {

    public SalaController(SalaService service) {
        super(service);
    }
}

