package com.example.prueba2.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.prueba2.models.Audiencia;
import com.example.prueba2.models.Sala;

import jakarta.transaction.Transactional;

@Repository
public interface AudienciaRepository extends JpaRepository<Audiencia, Integer> {

       @Query("SELECT a FROM Audiencia a WHERE a.audEstado = true")
       List<Audiencia> findAudienciasConEstadoTrue();

       // Borrado lógico
       @Transactional
       @Modifying
       @Query("UPDATE Audiencia a SET a.audEstado = false WHERE a.aud_id = :id")
       void borrarLogico(@Param("id") Integer id);

       // Encuentra todas las audiencias de una sala específica
       @Query("SELECT a FROM Audiencia a WHERE a.sala.sal_id = :salaId")
       List<Audiencia> encontrarPorSala(@Param("salaId") Integer salaId);

       // Obtiene todas las salas que tienen al menos una audiencia asignada
       @Query("SELECT DISTINCT a.sala FROM Audiencia a")
       List<Sala> encontrarSalasConAudiencias();

       // Cuenta cuántas audiencias tienen la misma autoridad en la misma fecha y hora
       @Query("SELECT COUNT(a) FROM Audiencia a " +
              "JOIN Audiencia_ext ae ON ae.audiencia.aud_id = a.aud_id " +
              "WHERE ae.autoridad.aut_id = :autoridadId " +
              "AND a.aud_fecha = :fecha " +
              "AND a.aud_hora = :hora")
       Long contarConflictos(@Param("autoridadId") Integer autoridadId,
                     @Param("fecha") LocalDate fecha,
                     @Param("hora") LocalTime hora);

       // Verifica si la sala ya tiene una audiencia en la misma fecha y hora
       @Query("SELECT COUNT(a) > 0 FROM Audiencia a " +
              "WHERE a.sala.sal_id = :salaId " +
              "AND a.aud_fecha = :fecha " +
              "AND a.aud_hora = :hora")
       boolean salaOcupada(@Param("salaId") Integer salaId,
                    @Param("fecha") LocalDate fecha,
                    @Param("hora") LocalTime hora);

       @Query("SELECT a FROM Audiencia a WHERE a.sala.id IN (SELECT s.id FROM Sala s WHERE s.distrito.id = :distritoId)")
       List<Audiencia> findByDistritoId(@Param("distritoId") Long distritoId);


}