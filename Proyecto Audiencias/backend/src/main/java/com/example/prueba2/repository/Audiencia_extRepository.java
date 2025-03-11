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
public interface Audiencia_extRepository extends JpaRepository<Audiencia_ext, Integer> {

    @Query("SELECT ae FROM Audiencia_ext ae " +
            "JOIN ae.audiencia a " +
            "WHERE ae.autoridad.aut_id = :autoridadId " +
            "AND a.aud_fecha = :fecha " +
            "AND a.aud_hora = :hora")
    List<Audiencia_ext> encontrarConflictos(Integer autoridadId, LocalDate fecha, java.time.LocalTime hora);

    List<Audiencia_ext> findByEauEstadoTrue();

    @Transactional
    @Modifying
    @Query("UPDATE Audiencia_ext ae SET ae.eauEstado = false WHERE ae.eau_id = :id")
    void borrarLogico(Integer id);

    @Query("SELECT ae FROM Audiencia_ext ae WHERE ae.autoridad.aut_id = :autoridadId") // ✅ CORRECTO
    List<Audiencia_ext> encontrarPorAutoridad(Integer autoridadId);

    // Si aud_id en AudienciaExt es una relación (@ManyToOne) con la entidad
    // Audiencia, entonces aud_id no es un simple campo, sino un objeto completo de
    // tipo Audiencia. Así que tienes que acceder al ID a través del objeto:
    // ae.audiencia.aud_id
    @Query("SELECT ae FROM Audiencia_ext ae WHERE ae.audiencia.aud_id = :aud_id")
    List<Audiencia_ext> findByAudienciaId(Long aud_id);

}