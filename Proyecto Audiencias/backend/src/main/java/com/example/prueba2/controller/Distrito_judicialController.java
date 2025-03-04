package com.example.prueba2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Distrito_judicial;
import com.example.prueba2.services.Distrito_judicialService;

@RestController
@RequestMapping("/api/distritos")
@CrossOrigin(origins = "http://localhost:4200")
public class Distrito_judicialController extends BaseController<Distrito_judicial, Integer> {

    public Distrito_judicialController(Distrito_judicialService service) {
        super(service);
    }
}
