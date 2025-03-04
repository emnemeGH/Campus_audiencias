package com.example.prueba2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.services.AutoridadService;

@RestController
@RequestMapping("/api/autoridades")
@CrossOrigin(origins = "http://localhost:4200")
public class AutoridadController extends BaseController<Autoridad, Integer> {

    public AutoridadController(AutoridadService service) {
        super(service);
    }
}
