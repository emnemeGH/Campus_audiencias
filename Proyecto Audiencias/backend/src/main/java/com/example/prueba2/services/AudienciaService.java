package com.example.prueba2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.repository.AudienciaRepository;

@Service

public class AudienciaService {
    @Autowired
    private AudienciaRepository audienciaRepository;

    public List<Audiencia> obtenerAudiencias(){
        return audienciaRepository.findAll();
    }
}
