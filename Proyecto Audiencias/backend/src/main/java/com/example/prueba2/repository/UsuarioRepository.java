package com.example.prueba2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    void borrarLogico(@Param("id") Integer id);

    Optional<Usuario> findByUsrUsername(String usrUsrname);
    Optional<Usuario> findByUsrMail(String usrMail);

    // Método para verificar si un usuario con un nombre de usuario ya existe
    boolean existsByUsrUsername(String usrUsername);
    boolean existsByUsrMail(String usrMail);

}
