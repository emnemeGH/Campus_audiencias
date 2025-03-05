package com.example.prueba2.services;

import com.example.prueba2.services.impl.BaseServiceImpl;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba2.models.Autoridad;
import com.example.prueba2.repository.AutoridadRepository;

@Service
public class AutoridadService extends BaseServiceImpl<Autoridad, Integer> {

    private final AutoridadRepository autoridadRepository;

    @Autowired
    public AutoridadService(AutoridadRepository autoridadRepository) {
        this.autoridadRepository = autoridadRepository;
    }

    @Transactional
    public void borradoLogico(Integer id) {
        autoridadRepository.borrarLogico(id);
        //Autoridad autoridad = autoridadRepository.findById(id).orElse(null);
        //if (autoridad != null) {
        //    autoridad.setAut_estado(false);
        //    autoridadRepository.save(autoridad);
        //}
    }
}
