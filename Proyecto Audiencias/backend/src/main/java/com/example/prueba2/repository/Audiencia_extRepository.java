package com.example.prueba2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.prueba2.models.Audiencia_ext;

import jakarta.transaction.Transactional;

@Repository
public interface Audiencia_extRepository extends JpaRepository<Audiencia_ext, Integer>{
    
    @Query("SELECT ae FROM Audiencia_ext ae " +
       "JOIN ae.aud_id a " +
       "WHERE ae.autoridad.aut_id = :autoridadId " +  // ✅ Cambiado de ae.autoridad.aut_id a ae.aut_id.aut_id
       "AND a.aud_fecha = :fecha " +
       "AND a.aud_hora = :hora ")  // ✅ Uso correcto para boolean en JPQL
    List<Audiencia_ext> encontrarConflictos(Integer autoridadId, LocalDate fecha, java.time.LocalTime hora);

    List<Audiencia_ext> findByEauEstadoTrue();

    @Transactional
    @Modifying
    @Query("UPDATE Audiencia_ext ae SET ae.eauEstado = false WHERE ae.eau_id = :id")
    void borrarLogico(Integer id);


    @Query("SELECT ae FROM Audiencia_ext ae WHERE ae.autoridad.aut_id = :autoridadId") // ✅ CORRECTO
    List<Audiencia_ext> encontrarPorAutoridad(Integer autoridadId);
}