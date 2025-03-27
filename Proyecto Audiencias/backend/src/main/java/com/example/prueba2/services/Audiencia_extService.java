package com.example.prueba2.services;

import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.repository.Audiencia_extRepository;

@Service
public class Audiencia_extService extends BaseServiceImpl<Audiencia_ext, Integer> {

    @Autowired
    private Audiencia_extRepository audienciaExtRepository;

    @Autowired
    public Audiencia_extService(Audiencia_extRepository audienciaExtRepository) {
        this.audienciaExtRepository = audienciaExtRepository;
    }

    @Transactional
    public void borradoLogico(Integer id) {
    audienciaExtRepository.borrarLogico(id);
    }

    public List<Audiencia_ext> getByAudiencia(Integer audId) {
        return audienciaExtRepository.findByAudienciaIds(audId);
    }

    public Audiencia_ext actualizar(Integer id, Audiencia_ext audienciaExt) {
        Audiencia_ext existente = audienciaExtRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        existente.setAutoridad(audienciaExt.getAutoridad()); // Actualiza la autoridad
        return audienciaExtRepository.save(existente);
    }

}
