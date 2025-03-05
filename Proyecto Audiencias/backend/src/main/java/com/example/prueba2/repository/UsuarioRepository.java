package com.example.prueba2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.prueba2.models.Usuario;

import jakarta.transaction.Transactional;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    
    List<Usuario> findByUsrEstadoTrue();

    //Borrado lógico: Cambia usr_estado a false en la BD
    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.usrEstado = false WHERE u.usr_id = :id")
    void borrarLogico(Integer id);
}
