package com.example.prueba2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.prueba2.models.Autoridad;

import jakarta.transaction.Transactional;

@Repository

public interface AutoridadRepository extends JpaRepository<Autoridad, Integer>{
    
    List<Autoridad> findByAutEstadoTrue();

    //Borrado lógico: Cambia usr_estado a false en la BD
    @Transactional
    @Modifying
    @Query("UPDATE Autoridad u SET u.autEstado = false WHERE u.aut_id = :id")
    void borrarLogico(@Param("id") Integer id);  
    
    boolean existsByAutMail(String autMail);
}
