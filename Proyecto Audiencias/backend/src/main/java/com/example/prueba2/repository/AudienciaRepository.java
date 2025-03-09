package com.example.prueba2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.prueba2.models.Audiencia;
import com.example.prueba2.models.Audiencia_ext;

import jakarta.transaction.Transactional;

@Repository

public interface AudienciaRepository extends JpaRepository<Audiencia, Integer>{
    
    List<Audiencia> findByEauEstadoTrue();

    //Borrado lógico: Cambia usr_estado a false en la BD
    @Transactional
    @Modifying
    @Query("SELECT ae FROM Audiencia_ext ae WHERE ae.eauEstado = true") // ✅ CORRECTO
    List<Audiencia_ext> encontrarActivas();
    
    void borrarLogico(Integer id);
}
