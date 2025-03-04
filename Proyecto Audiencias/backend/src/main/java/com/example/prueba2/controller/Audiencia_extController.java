package com.example.prueba2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.services.Audiencia_extService;

@RestController
@RequestMapping("/api/audienciasExt")
@CrossOrigin(origins = "http://localhost:4200")
public class Audiencia_extController extends BaseController<Audiencia_ext, Integer> {

    public Audiencia_extController(Audiencia_extService service) {
        super(service);
    }
}