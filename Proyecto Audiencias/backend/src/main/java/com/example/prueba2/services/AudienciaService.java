package com.example.prueba2.services;

import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.repository.AudienciaRepository;
import com.example.prueba2.repository.Audiencia_extRepository;

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

    @Autowired
    private Audiencia_extRepository Audiencia_extRepository;

    public List<Audiencia_ext> obtenerAutoridadesPorAudiencia(Long aud_id) {
        return Audiencia_extRepository.findByAudienciaId(aud_id);
    }
}