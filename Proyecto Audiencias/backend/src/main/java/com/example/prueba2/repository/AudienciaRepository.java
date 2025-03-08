package com.example.prueba2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.prueba2.models.Audiencia;

import jakarta.transaction.Transactional;

@Repository

public interface AudienciaRepository extends JpaRepository<Audiencia, Integer>{
    
    List<Audiencia> findByAutEstadoTrue();

    //Borrado lógico: Cambia usr_estado a false en la BD
    @Transactional
    @Modifying
    @Query("UPDATE Audiencia u SET u.audEstado = false WHERE u.aud_id = :id")
    void borrarLogico(Integer id);
}
