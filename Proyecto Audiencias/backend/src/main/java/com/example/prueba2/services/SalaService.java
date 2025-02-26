package com.example.prueba2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Sala;
import com.example.prueba2.repository.SalaRepository;

@Service

public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> obtenerSalas() {
        return salaRepository.findAll();
    }

}
