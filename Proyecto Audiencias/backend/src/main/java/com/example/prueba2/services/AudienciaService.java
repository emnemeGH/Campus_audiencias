package com.example.prueba2.services;

import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.repository.AudienciaRepository;

@Service
public class AudienciaService extends BaseServiceImpl<Audiencia, Integer> {
    
    @Autowired
    private AudienciaRepository audienciaRepository;

    @Autowired
    public AudienciaService(AudienciaRepository audienciaRepository ) {
        this.audienciaRepository = audienciaRepository;
    }

    @Transactional
    public void borradoLogico(Integer id) {
        audienciaRepository.borrarLogico(id);
    }
}