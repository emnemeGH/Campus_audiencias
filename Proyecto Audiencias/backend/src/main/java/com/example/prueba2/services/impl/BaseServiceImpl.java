package com.example.prueba2.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba2.services.BaseService;

import jakarta.persistence.EntityNotFoundException;

public abstract class BaseServiceImpl<TipoEntidad, TipoID> implements BaseService<TipoEntidad, TipoID> {
    
    @Autowired
    protected JpaRepository<TipoEntidad, TipoID> repository;

    @Override
    public List<TipoEntidad> obtenerEntidades() {
        return repository.findAll();
    }

    @Override
    public Optional<TipoEntidad> obtenerPorId(TipoID id){
        return repository.findById(id);
    }

    @Override
    public TipoEntidad guardar(TipoEntidad entidad) {
        return repository.save(entidad);
    }

    @Override
    public TipoEntidad actualizar(TipoEntidad entidad, TipoID id) {
    if (repository.existsById(id)) {  // Verifica si existe antes de actualizar
        return repository.save(entidad);
    } else {
        throw new EntityNotFoundException("No se encontró la entidad con ID: " + id);
    }
}
}

