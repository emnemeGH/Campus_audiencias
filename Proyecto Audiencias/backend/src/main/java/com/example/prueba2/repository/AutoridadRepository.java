package com.example.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba2.models.Autoridad;

@Repository

public interface AutoridadRepository extends JpaRepository<Autoridad, Integer>{
    
}
