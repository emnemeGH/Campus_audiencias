package com.example.prueba2.services;

import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.models.Distrito_judicial;
import com.example.prueba2.repository.Audiencia_extRepository;
import com.example.prueba2.repository.AutoridadRepository;
import com.example.prueba2.repository.Distrito_judicialRepository;

@Service
public class AutoridadService extends BaseServiceImpl<Autoridad, Integer> {

    private final AutoridadRepository autoridadRepository;

    @Autowired
    private Audiencia_extRepository audienciaExtRepository;

    @Autowired
    private Distrito_judicialRepository distritoRepository;

    @Autowired
    public AutoridadService(AutoridadRepository autoridadRepository) {
        this.autoridadRepository = autoridadRepository;
    }

    @Transactional
    public void borradoLogico(Integer id) {
        if (audienciaExtRepository.tieneAudienciasActivas(id)) {
            throw new IllegalStateException("No se puede eliminar la autoridad porque tiene audiencias activas.");
        }
        autoridadRepository.borrarLogico(id);
    }

    public Autoridad guardarAutoridad(Autoridad autoridad) {
        if (autoridadRepository.existsByAutMail(autoridad.getAutMail())) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }

        if (autoridad.getDistrito() == null || autoridad.getDistrito().getDis_id() == null) {
            throw new IllegalArgumentException("El distrito es obligatorio.");
        }

        // Buscar el distrito en la base de datos
        Distrito_judicial distrito = distritoRepository.findById(autoridad.getDistrito().getDis_id())
                .orElseThrow(() -> new IllegalArgumentException("El distrito no existe."));

        // Asignar el distrito a la autoridad antes de guardarla
        autoridad.setDistrito(distrito);

        return autoridadRepository.save(autoridad);
    }

    public Autoridad actualizarAutoridad(Integer id, Autoridad nuevaAutoridad) {
        Autoridad autoridad = autoridadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autoridad no encontrada"));

        // Validamos que los valores no sean nulos antes de asignarlos
        if (nuevaAutoridad.getAut_nombre() != null) {
            autoridad.setAut_nombre(nuevaAutoridad.getAut_nombre());
        }
        if (nuevaAutoridad.getAutMail() != null) {
            autoridad.setAutMail(nuevaAutoridad.getAutMail());
        }
        if (nuevaAutoridad.getAut_tipo() != null) {
            autoridad.setAut_tipo(nuevaAutoridad.getAut_tipo());
        }
        if (nuevaAutoridad.getDistrito() != null) {
            autoridad.setDistrito(nuevaAutoridad.getDistrito());
        }

        return autoridadRepository.save(autoridad);
    }

}