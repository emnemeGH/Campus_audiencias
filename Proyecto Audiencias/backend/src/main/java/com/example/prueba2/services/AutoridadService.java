package com.example.prueba2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.repository.AutoridadRepository;
@Service

public class AutoridadService {
    @Autowired
    private AutoridadRepository autoridadRepository;
    
    public List<Autoridad> obtenerAutoridades(){
        return autoridadRepository.findAll();
    }
}
