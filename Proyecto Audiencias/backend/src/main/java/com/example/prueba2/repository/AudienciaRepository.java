package com.example.prueba2.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.prueba2.models.Audiencia;

import jakarta.transaction.Transactional;

@Repository

public interface AudienciaRepository extends JpaRepository<Audiencia, Integer>{
    
    //Metodo estado
    List<Audiencia> findByAudEstadoTrue();

    //Borrado lógico
    @Transactional
    @Modifying
    @Query("SELECT a FROM Audiencia a WHERE a.audEstado = true")
    //List<Audiencia> encontrarActivas();
    void borrarLogico(Integer id);

    //Este método verifica si la autoridad ya tiene una audiencia en la misma fecha y hora.
    @Query("SELECT COUNT(a) FROM Audiencia a " +
       "JOIN Audiencia_ext ae ON ae.audiencia.aud_id = a.aud_id " +
       "WHERE ae.autoridad.aut_id = :autoridadId " +
       "AND a.aud_fecha = :fecha " +
       "AND a.aud_hora = :hora")
    Long contarConflictos(Integer autoridadId, LocalDate fecha, LocalTime hora);
}
