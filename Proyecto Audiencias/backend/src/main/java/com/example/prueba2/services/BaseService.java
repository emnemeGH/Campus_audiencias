package com.example.prueba2.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<TipoEntidad, TipoID> {
    List<TipoEntidad> obtenerEntidades();
    Optional<TipoEntidad> obtenerPorId(TipoID id);
    TipoEntidad guardar(TipoEntidad entidad);
}
