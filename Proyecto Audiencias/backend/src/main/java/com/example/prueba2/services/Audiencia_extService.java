package com.example.prueba2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.repository.Audiencia_extRepository;

@Service
public class Audiencia_extService {
    @Autowired
    private Audiencia_extRepository audienciaExtRepository;

    public List<Audiencia_ext> obtenerAudienciaExt(){
        return audienciaExtRepository.findAll();
    }
}

