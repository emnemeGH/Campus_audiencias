package com.example.prueba2.services;

import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.models.Audiencia_ext;
import com.example.prueba2.models.Usuario;
import com.example.prueba2.repository.AudienciaRepository;
import com.example.prueba2.repository.Audiencia_extRepository;
import com.example.prueba2.repository.UsuarioRepository;

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

    public Audiencia guardarAudiencia(Audiencia audiencia, Integer autoridadId) {
        if (autoridadId == null || audiencia.getAud_fecha() == null || audiencia.getAud_hora() == null) {
            throw new IllegalArgumentException("Debe seleccionar una autoridad, fecha y hora válidas.");
        }
    
        // Verificar si la autoridad ya tiene una audiencia en la misma fecha y hora
        Long conflictos = audienciaRepository.contarConflictos(
            autoridadId,
            audiencia.getAud_fecha(),
            audiencia.getAud_hora()
        );
    
        if (conflictos > 0) {
            throw new IllegalArgumentException("La autoridad ya tiene una audiencia en la misma fecha y hora.");
        }
    
        return audienciaRepository.save(audiencia);
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }
}