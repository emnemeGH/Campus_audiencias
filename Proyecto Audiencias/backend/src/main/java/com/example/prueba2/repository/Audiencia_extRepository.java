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
           "WHERE ae.aut_id.aut_id = :autoridadId " +
           "AND a.aud_fecha = :fecha " +
           "AND a.aud_hora = :hora")
    List<Audiencia_ext> encontrarConflictos(Integer eau_id, LocalDate eau_fecins);

    List<Audiencia_ext> findByAutEstadoTrue();

    //Borrado lógico: Cambia usr_estado a false en la BD
    @Transactional
    @Modifying
    @Query("UPDATE Audiencia_ext u SET u.eauEstado = false WHERE u.eau_id = :id")
    void borrarLogico(Integer id);
}
