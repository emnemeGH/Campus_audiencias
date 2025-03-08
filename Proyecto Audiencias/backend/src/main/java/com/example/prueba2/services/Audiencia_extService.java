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

    public Audiencia_ext guardarAudienciaExt(Audiencia_ext audienciaExt) {
        // Validar si la autoridad ya tiene una audiencia en la misma fecha y hora
        List<Audiencia_ext> conflictos = audienciaExtRepository.encontrarConflictos(
            audienciaExt.getAut_id().getAut_id(),
            audienciaExt.getAud_id().getAud_fecha()
        );

        if (!conflictos.isEmpty()) {
            throw new IllegalArgumentException("La autoridad ya tiene una audiencia en la misma fecha y hora.");
        }

        // Si no hay conflictos, guardar la audiencia
        return audienciaExtRepository.save(audienciaExt);
    }
}
