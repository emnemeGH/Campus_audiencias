package com.example.prueba2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.services.AutoridadService;

@RestController
@RequestMapping("/api/autoridades")
@CrossOrigin(origins = "http://localhost:4200")
public class AutoridadController extends BaseController<Autoridad, Integer> {

    private AutoridadService autoridadService;

    public AutoridadController(AutoridadService autoridadService) {
        super(autoridadService);
        this.autoridadService = autoridadService;
    }

    @DeleteMapping(("/{id}"))
    public void eliminarPorId(Integer id) {
        autoridadService.borradoLogico(id);
    }
}
