package com.example.prueba2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Distrito_judicial;
import com.example.prueba2.repository.Distrito_judicialRepository;

@Service

public class Distrito_judicialService {
    @Autowired
    private Distrito_judicialRepository distritoRepository;

    public List<Distrito_judicial> obtenerDistritos() {
        return distritoRepository.findAll();
    }
}
