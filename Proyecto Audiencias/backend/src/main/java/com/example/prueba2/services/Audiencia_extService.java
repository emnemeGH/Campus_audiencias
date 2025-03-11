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
        
        if (audienciaExt.getAutoridad() == null || audienciaExt.getAudiencia() == null) {
            throw new IllegalArgumentException("Debe seleccionar una autoridad y una audiencia válida.");
        }
    
        // List<Audiencia_ext> conflictos
        // Declara una lista de objetos de tipo Audiencia_ext llamada conflictos.
        List<Audiencia_ext> conflictos = audienciaExtRepository.encontrarConflictos(
            // audienciaExt.getAutoridad().getAut_id(): Obtiene el ID de la autoridad asociada a la audiencia.
            audienciaExt.getAutoridad().getAut_id(),
            audienciaExt.getAudiencia().getAud_fecha(),
            audienciaExt.getAudiencia().getAud_hora()
        );
    
        if (!conflictos.isEmpty()) {
            throw new IllegalArgumentException("La autoridad ya tiene una audiencia en la misma fecha y hora.");
        }
    
        return audienciaExtRepository.save(audienciaExt);
    }
}
