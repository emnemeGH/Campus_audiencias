package com.example.prueba2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.prueba2.services.BaseService;

public abstract class BaseController<TipoEntidad, TipoID> {
    
    private final BaseService<TipoEntidad, TipoID> service;

    public BaseController(BaseService<TipoEntidad, TipoID> service){
        this.service = service;
    }

    @GetMapping
    public List<TipoEntidad> obtenerEntidades(){
        return service.obtenerEntidades();
    }

    @GetMapping("/{id}")
    public Optional<TipoEntidad> obtenerPorId(@PathVariable TipoID id){
        return service.obtenerPorId(id);
    }

    @PostMapping
    public TipoEntidad guardar(@RequestBody TipoEntidad entidad){
        return service.guardar(entidad);
    }

}
