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
    
    //Borrado lógico
    @Transactional
    @Modifying
    @Query("SELECT a FROM Audiencia a WHERE a.audEstado = true")
    List<Audiencia> encontrarActivas();
    
    //Metodo estado
    List<Audiencia> findByAudEstadoTrue();

    void borrarLogico(Integer id);
}
